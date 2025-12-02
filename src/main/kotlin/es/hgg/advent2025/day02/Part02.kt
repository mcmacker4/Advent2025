package es.hgg.advent2025.day02

import es.hgg.advent2025.common.ChallengeInput
import es.hgg.advent2025.common.ExampleInput
import es.hgg.advent2025.common.PuzzleInput
import es.hgg.advent2025.common.readText
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

fun main() {
    Day02Part02(ChallengeInput(2)).run()
}

class Day02Part02(val input: PuzzleInput) {

    fun run() {
        val (input, readDuration) = measureTimedValue { input.readText() }

        println("Took $readDuration to read input")

        val (result, computeDuration) = measureTimedValue {

            input.splitToSequence(",")
                .flatMap { it.parseRange().findInvalidIds() }
                .sum()
        }

        println(result)
        println("Took $computeDuration to compute")
    }

}

private fun LongRange.findInvalidIds(): Sequence<Long> = asSequence().filter { id ->
    id.toString().isInvalid()
}

private fun String.isInvalid(): Boolean {
    for (cursorCount in (2..length).filter { length % it == 0 }) {
        val stride = length / cursorCount

        val invalid = (0..<stride).all { offset ->
            (0..<cursorCount).map<Int, Char> { this[it * stride + offset] }.allEqual()
        }

        if (invalid) return true
    }

    return false
}

private fun String.parseRange(): LongRange {
    val (left, right) = split("-")
    return LongRange(left.toLong(), right.toLong())
}

fun<T> List<T>.allEqual() = asSequence().drop(1).all { it == this[0] }