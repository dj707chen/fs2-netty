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

import io.netty.buffer.ByteBufAllocator
import io.netty.channel.{ ChannelOption => JChannelOption, MessageSizeEstimator, RecvByteBufAllocator, WriteBufferWaterMark }

import java.lang.{ Boolean => JBoolean }

sealed trait ChannelOption {
  type Value
  val key:   JChannelOption[Value]
  val value: Value
}

object ChannelOption {

  def apply[A](key0: JChannelOption[A], value0: A): ChannelOption =
    new ChannelOption {
      type Value = A
      val key   = key0
      val value = value0
    }

  def allocator(value: ByteBufAllocator): ChannelOption =
    apply(JChannelOption.ALLOCATOR, value)

  def allowHalfClosure(value: Boolean): ChannelOption =
    apply(JChannelOption.ALLOW_HALF_CLOSURE, JBoolean.valueOf(value))

  def autoClose(value: Boolean): ChannelOption =
    apply(JChannelOption.AUTO_CLOSE, JBoolean.valueOf(value))

  // we don't allow reconfiguring auto-read because it corrupts backpressure entirely

  def connectTimeoutMillis(value: Int): ChannelOption =
    apply(JChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.valueOf(value))

  // TODO multicast options

  def ipTos(value: Int): ChannelOption =
    apply(JChannelOption.IP_TOS, Integer.valueOf(value))

  def messageSizeEstimator(value: MessageSizeEstimator): ChannelOption =
    apply(JChannelOption.MESSAGE_SIZE_ESTIMATOR, value)

  def rcvBufAllocator(value: RecvByteBufAllocator): ChannelOption =
    apply(JChannelOption.RCVBUF_ALLOCATOR, value)

  // TODO tune executor things?

  def backlog(value: Int): ChannelOption =
    apply(JChannelOption.SO_BACKLOG, Integer.valueOf(value))

  def broadcast(value: Boolean): ChannelOption =
    apply(JChannelOption.SO_BROADCAST, JBoolean.valueOf(value))

  def keepAlive(value: Boolean): ChannelOption =
    apply(JChannelOption.SO_KEEPALIVE, JBoolean.valueOf(value))

  def linger(value: Int): ChannelOption =
    apply(JChannelOption.SO_LINGER, Integer.valueOf(value))

  def receiveBuffer(value: Int): ChannelOption =
    apply(JChannelOption.SO_RCVBUF, Integer.valueOf(value))

  def reuseAddress(value: Boolean): ChannelOption =
    apply(JChannelOption.SO_REUSEADDR, JBoolean.valueOf(value))

  def sendBuffer(value: Int): ChannelOption =
    apply(JChannelOption.SO_SNDBUF, Integer.valueOf(value))

  def timeout(value: Int): ChannelOption =
    apply(JChannelOption.SO_TIMEOUT, Integer.valueOf(value))

  def noDelay(value: Boolean): ChannelOption =
    apply(JChannelOption.TCP_NODELAY, JBoolean.valueOf(value))

  def writeBufferWaterMark(value: WriteBufferWaterMark): ChannelOption =
    apply(JChannelOption.WRITE_BUFFER_WATER_MARK, value)

  def writeSpinCount(value: Int): ChannelOption =
    apply(JChannelOption.WRITE_SPIN_COUNT, Integer.valueOf(value))
}
