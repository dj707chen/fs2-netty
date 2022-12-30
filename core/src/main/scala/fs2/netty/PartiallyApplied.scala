/*
 * Copyright 2021 Typelevel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fs2.netty

import cats.effect.{ Async, Sync }
import cats.syntax.all._

import io.netty.util.concurrent.Future

import java.util.concurrent.CancellationException

private final class PartiallyApplied[F[_]] extends PartiallyAppliedPlatform[F] {
  def apply[A](ff: F[Future[A]])(implicit F: Async[F]): F[A] = {
    def inner(fut: Future[A], cancelable: Boolean): F[A] =
      Async[F].async[A] { cb =>
        Sync[F].delay {
          fut.addListener { (fut: Future[A]) => // intentional shadowing
            if (fut.isSuccess()) {
              cb(Right(fut.getNow()))
            } else {
              fut.cause() match {
                case _: CancellationException if cancelable =>
                  () // swallow this one since it *probably* means we were canceled
                case t => cb(Left(t))
              }
            }
          }

          if (fut.isCancellable() && cancelable)
            Some(
              Sync[F].delay(fut.cancel(false)) >> inner(fut, false).void,
            ) // await the cancelation
          else
            None
        }
      }

    ff.flatMap(inner(_, true))
  }
}
