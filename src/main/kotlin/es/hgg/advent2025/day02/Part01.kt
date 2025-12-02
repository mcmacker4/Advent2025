package es.hgg.advent2025.day02

import es.hgg.advent2025.common.ChallengeInput
import es.hgg.advent2025.common.PuzzleInput
import es.hgg.advent2025.common.readText

fun main() {
    Day02Part01(ChallengeInput(2)).run()
}

class Day02Part01(val input: PuzzleInput) {

    fun run() {
        val input = input.readText()

        val result = input.splitToSequence(",")
            .flatMap { it.parseRange().findInvalidIds() }
            .sum()

        println(result)
    }

}

private fun LongRange.findInvalidIds(): Sequence<Long> = asSequence().filter { id ->
    id.toString().let { it.length % 2 == 0 && it.take(it.length / 2) == it.substring(it.length / 2) }
}

private fun String.parseRange(): LongRange {
    val (left, right) = split("-")
    return LongRange(left.toLong(), right.toLong())
}