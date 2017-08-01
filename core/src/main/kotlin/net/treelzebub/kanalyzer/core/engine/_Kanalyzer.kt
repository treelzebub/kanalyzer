package net.treelzebub.kanalyzer.core.engine

import net.treelzebub.kanalyzer.core.cli.AnalysisTask

fun kanalyze(tasks: List<AnalysisTask>) {
  if (AnalysisTask.Lint in tasks) {
    lint()
  }

  if (AnalysisTask.Optimization in tasks) {
    optimization()
  }

  if (AnalysisTask.Security in tasks) {
    security()
  }

  println("End.")
}

private fun lint() {
  println("Lint!")
}

private fun optimization() {
  println("Optimization!")
}

private fun security() {
  println("Security!")
}