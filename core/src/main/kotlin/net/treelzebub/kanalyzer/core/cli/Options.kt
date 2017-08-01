package net.treelzebub.kanalyzer.core.cli

import org.apache.commons.cli.Option
import org.apache.commons.cli.Options


private fun Options.addOption(name: String, fn: Option.Builder.() -> Unit)
    = apply { Option.builder(name).apply(fn).build() }

val ALL_OPTIONS = Options()
    .addOption("-i") {
      optionalArg(false)
      longOpt("input")
      hasArg(true)
      argName("in-source")
      desc("Path to input to be kanalyzed. Must also set (-t or --type)")
    }
    .addOption("t") {
      optionalArg(false)
      longOpt("type")
      hasArg(true)
      argName("type")
      desc("Source type [\"binary\" or \"source\"]")
    }
    .addOption("") {
      longOpt("version")
      desc("Prints the version, then exits.")
    }
