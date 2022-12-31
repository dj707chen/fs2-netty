# My Test

```shell
sbt "benchmarks/runMain fs2.netty.benchmarks.echo.RawNetty localhost 8081"
./throughput.sh localhost 8081
: << 'END'
Ramped up to 500 connections.
Total data sent:     40666.9 MiB (42642313412 bytes)
Total data received: 40664.1 MiB (42639449000 bytes)
Bandwidth per channel: 45.482⇅ Mbps (5685.2 kBps)
Aggregate bandwidth: 11370.085↓, 11370.849↑ Mbps
Packet rate estimate: 1025666.8↓, 999735.4↑ (9↓, 14↑ TCP MSS/op)
Test duration: 30.0011 s.
END

sbt "benchmarks/runMain fs2.netty.benchmarks.echo.Fs2Netty localhost 8082"
./throughput.sh localhost 8082
: << 'END'
Ramped up to 500 connections.
Total data sent:     19569.7 MiB (20520295533 bytes)
Total data received: 12438.9 MiB (13043180784 bytes)
Bandwidth per channel: 17.899⇅ Mbps (2237.4 kBps)
Aggregate bandwidth: 3477.851↓, 5471.558↑ Mbps
Packet rate estimate: 318642.9↓, 477779.4↑ (12↓, 24↑ TCP MSS/op)
Test duration: 30.0029 s.
END

sbt "benchmarks/runMain fs2.netty.benchmarks.echo.Fs2IO localhost 8083"
./throughput.sh localhost 8083
: << 'END'
Ramped up to 500 connections.
Total data sent:     14219.2 MiB (14909943276 bytes)
Total data received: 14218.4 MiB (14909095936 bytes)
Bandwidth per channel: 15.901⇅ Mbps (1987.7 kBps)
Aggregate bandwidth: 3975.221↓, 3975.447↑ Mbps
Packet rate estimate: 363897.2↓, 340645.6↑ (6↓, 9↑ TCP MSS/op)
Test duration: 30.0041 s.
END
```
