package net.treelzebub.kanalyzer

import net.treelzebub.kanalyzer.core.cli.ALL_OPTIONS
import net.treelzebub.kanalyzer.core.cli.AnalysisTask
import net.treelzebub.kanalyzer.core.cli.Log
import net.treelzebub.kanalyzer.core.cli.Log.verbosity
import net.treelzebub.kanalyzer.core.cli.Verbosity
import net.treelzebub.kanalyzer.core.engine.kanalyze
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.ParseException

fun main(vararg args: String) {
  val parser = DefaultParser()
  val commandLine: CommandLine

  try {
    commandLine = parser.parse(ALL_OPTIONS, args, false)
  } catch (e: ParseException) {
    System.err.println(e.message)
    usage()
    return
  }

  val tasks = parseOptions(commandLine)
  kanalyze(tasks)
}

// Set up logging and task switches. Return list of user's requested tasks.
private fun parseOptions(commandLine: CommandLine): List<AnalysisTask> {
  val opts = commandLine.options.map { it.opt }

  // Logging
  if ("-v" in opts || "--verbose" in opts) {
    verbosity = Verbosity.Verbose
  } else if ("-q" in opts || "--quiet" in opts) {
    verbosity = Verbosity.Quiet
  }
  Log.init()

  // Parsing
  val tasks = mutableListOf<AnalysisTask>()
  if ("-l" in opts || "--lint" in opts) {
    tasks.add(AnalysisTask.Lint)
  } else if ("-o" in opts || "--optimization" in opts) {
    tasks.add(AnalysisTask.Optimization)
  } else if ("-s" in opts || "--security" in opts) {
    tasks.add(AnalysisTask.Security)
  }
  return tasks
}

private fun usage() {
  println("""
        Kanalyzer Usage:
        ......
    """)
}