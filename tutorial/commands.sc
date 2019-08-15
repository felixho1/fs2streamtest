object Yolo {
  import scala.concurrent.ExecutionContext
  import cats.effect.{ContextShift, IO, Timer}

  val blockingEc = ExecutionContext.fromExecutor(java.util.concurrent.Executors.newCachedThreadPool)
  implicit val timerIO: Timer[IO] = IO.timer(ExecutionContext.global)
  implicit val contextShiftIO: ContextShift[IO] = IO.contextShift(ExecutionContext.global)
}

import fs2.Stream
val s = Stream(1,2,3) // pure stream
s.toList
s.toVector
val res4 = s.map(_ + 1)
res4.toList
val res6 = s.flatMap(i => Stream(i,i,i))
res6.toList
s.toList
s.repeat
// dont : s.repeat.toList
s.repeat.take(10).toList
val posints = Stream.unfold(1)(i => Some((i, i+1)))
posints.take(10).toList // 1 to 10
val res13 = posints.zip(s.repeat)
res13.take(15).toList
val r = Stream.randomSeeded(0L)
r.take(10).toList
r.take(10).toList

import cats.effect.IO
val prg = IO(println("Hello World!"))
prg.unsafeRunSync()
val p = IO.pure(42)
p.map(_ + 1)

import fs2.io
import Yolo._
val bytes = io.file.readAll[IO](java.nio.file.Paths.get("data/sample.json"), blockingEc, 4096)
// bytes : Stream[IO, Byte]
bytes.fold(0)((acc, b) => acc + 1) // Stream[IO, Int]
