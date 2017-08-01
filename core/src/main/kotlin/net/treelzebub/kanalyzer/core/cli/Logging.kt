package net.treelzebub.kanalyzer.core.cli

import java.util.logging.*


enum class Verbosity {
  Quiet,
  Normal,
  Verbose
}

object Log {

  private val DEFAULT_VERBOSITY = Verbosity.Normal

  var verbosity = DEFAULT_VERBOSITY

  fun init() {
    val logger = Logger.getLogger("")
    for (handler in logger.handlers) {
      logger.removeHandler(handler)
    }
    LogManager.getLogManager().reset()

    if (verbosity == Verbosity.Quiet) {
      return
    }

    val handler = object : Handler() {
      override fun publish(record: LogRecord) {
        if (formatter == null) {
          formatter = SimpleFormatter()
        }

        try {
          val message = formatter.format(record)
          if (record.level.intValue() >= Level.WARNING.intValue()) {
            System.err.write(message.toByteArray())
          } else {
            if (record.level.intValue() >= Level.INFO.intValue()) {
              System.out.write(message.toByteArray())
            } else {
              if (verbosity == Verbosity.Verbose) {
                System.out.write(message.toByteArray())
              }
            }
          }
        } catch (exception: Exception) {
          reportError(null, exception, ErrorManager.FORMAT_FAILURE)
        }
      }

      override fun close() {}
      override fun flush() {}
    }

    logger.addHandler(handler)

    if (verbosity == Verbosity.Verbose) {
      handler.level = Level.ALL
      logger.level = Level.ALL
    } else {
      handler.formatter = object : Formatter() {
        override fun format(record: LogRecord): String {
          return "${record.level.toString()[0]}: ${record.message} + ${System.getProperty("line.separator")}"
        }
      }
    }
  }
}