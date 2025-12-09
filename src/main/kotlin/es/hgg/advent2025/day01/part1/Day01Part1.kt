package es.hgg.advent2025.day01.part1

import es.hgg.advent2025.common.challengeInput

fun main() {
    val (position, count) = challengeInput(1) { reader ->
        reader.lineSequence().map { line ->
            print(line)
            when {
                line.startsWith("L") -> -line.substring(1).toInt()
                else -> line.substring(1).toInt()
            }
        }.fold(50 to 0) { (position, count), move ->
            var sum = (position + move) % 100
            if (sum < 0) sum += 100

            println(" -> $position to $sum")

            sum to (if (sum == 0) count + 1 else count)
        }
    }

    println("Last position was $position, count is $count")
}