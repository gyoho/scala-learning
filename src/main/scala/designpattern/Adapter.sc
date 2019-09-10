import java.util.logging.Level
import java.util.logging.Level._

trait Log {
  def warning(message: String): Unit
  def error(message: String): Unit
}

final class Logger {
  def log(level: Level, message: String): Unit = ???
}

implicit class LoggerToLogAdapter(logger: Logger) extends Log {
  def warning(message: String): Unit = logger.log(WARNING, message)
  def error(message: String): Unit = logger.log(INFO, message)
}

val log: Log = new Logger() // implicit class will convert Logger to Log