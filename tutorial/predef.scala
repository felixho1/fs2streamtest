import $ivy.`co.fs2::fs2-io:1.0.4`

interp.configureCompiler(_.settings.YpartialUnification.value = true)
interp.configureCompiler(_.settings.Ydelambdafy.tryToSetColon(List("inline")))

def disablePrettyPrintIO = repl.pprinter.update(repl.pprinter().copy(additionalHandlers = {
  case io: cats.effect.IO[_] => pprint.Tree.Literal("* * *")
}))

disablePrettyPrintIO

def enablePrettyPrintIO = repl.pprinter.update(repl.pprinter().copy(additionalHandlers = PartialFunction.empty))

object Yolo {
  import scala.concurrent.ExecutionContext
  import cats.effect.{ContextShift, IO, Timer}

  val blockingEc = ExecutionContext.fromExecutor(java.util.concurrent.Executors.newCachedThreadPool)
  implicit val timerIO: Timer[IO] = IO.timer(ExecutionContext.global)
  implicit val contextShiftIO: ContextShift[IO] = IO.contextShift(ExecutionContext.global)
}