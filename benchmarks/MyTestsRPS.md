# My Test

## Install the Rust tool cargo
```shell
curl https://sh.rustup.rs -sSf | sh
cd ~/repoOSS
git clone https://github.com/haraldh/rust_echo_bench.git
cd rust_echo_bench
```

```shell
cd /Users/dj.chen/repoMy/fs2-netty; sbt "benchmarks/runMain fs2.netty.benchmarks.echo.RawNetty localhost 8081"
cd /Users/dj.chen/repooss/rust_echo_bench; cargo run --release -- --address "127.0.0.1:8081" --number 200 --duration 30 --length 128
: << 'END'
Speed: 140252 request/sec, 140251 response/sec
Requests: 4207589
Responses: 4207544
END

cd /Users/dj.chen/repoMy/fs2-netty; sbt "benchmarks/runMain fs2.netty.benchmarks.echo.Fs2Netty localhost 8082"
cd /Users/dj.chen/repooss/rust_echo_bench; cargo run --release -- --address "127.0.0.1:8082" --number 200 --duration 30 --length 128
: << 'END'
Speed: 69416 request/sec, 69414 response/sec
Requests: 2082491
Responses: 2082420
END

cd /Users/dj.chen/repoMy/fs2-netty; sbt "benchmarks/runMain fs2.netty.benchmarks.echo.Fs2IO localhost 8083"
cd /Users/dj.chen/repooss/rust_echo_bench; cargo run --release -- --address "127.0.0.1:8083" --number 200 --duration 30 --length 128
: << 'END'
Speed: 102388 request/sec, 102383 response/sec
Requests: 3071655
Responses: 3071506
END
```
