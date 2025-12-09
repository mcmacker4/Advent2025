package es.hgg.advent2025.day05.part1

import es.hgg.advent2025.common.challengeInput


fun main() {
    challengeInput(5) { input ->
       val lines = input.readLines()

       val ranges = lines
           .takeWhile { it.isNotEmpty() }
           .map {
               val parts = it.split("-")
               parts[0].toLong()..parts[1].toLong()
           }
           .toList()

       val count = lines
           .drop(ranges.size + 1)
           .map { it.toLong() }
           .count { ingredient ->
               ranges.any { range -> ingredient in range }
           }

       println("Count: $count")

   }
}