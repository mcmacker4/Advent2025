package es.hgg.advent2025.day05.part2

import es.hgg.advent2025.common.challengeInput
import java.util.*
import kotlin.math.max
import kotlin.math.min

fun main() = challengeInput(5) { input ->
    val lines = input.lineSequence()

    val ranges = sortedSetOf<LongRange>({ a, b ->
        when {
            // Equal by reference
            a === b -> 0
            // A is to the right, both from A are greater than B's last
            a.first > b.last && a.last > b.last -> 1
            // B is to the right, both from B are greater than A's last
            b.first > a.last && b.last > a.last -> -1
            else -> throw Error("Ranges overlap: $a, $b")
        }
    })

    lines
        .takeWhile { it.isNotEmpty() }
        .map {
            val parts = it.split("-")
            parts[0].toLong()..parts[1].toLong()
        }
        .forEach {
            ranges.addDeduped(it)
        }

    val totalIds = ranges.sumOf { it.last - it.first + 1 }

    println("Result: $totalIds")

}

private fun TreeSet<LongRange>.addDeduped(inRange: LongRange) {
    var range: LongRange? = inRange
    val dedupedRanges = mutableListOf<LongRange>()

    for (existing in this) {
        if (range == null)
            break

        val left = if (existing.first > range.first) (range.first)..min(range.last, existing.first - 1) else null
        val right = if (range.last > existing.last) max(range.first, existing.last + 1)..range.last else null

        left?.let { dedupedRanges += it }
        range = right
    }

    range?.let { add(it) }
    addAll(dedupedRanges)
}
