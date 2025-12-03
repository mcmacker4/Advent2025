package es.hgg.advent2025.day01

import es.hgg.advent2025.common.ChallengeInput
import es.hgg.advent2025.common.useLines
import kotlin.math.abs

fun main() {
    val (position, count) = ChallengeInput(1).useLines { lines ->
        lines.map { line ->
            when {
                line.startsWith("L") -> -line.substring(1).toInt()
                else -> line.substring(1).toInt()
            }
        }.fold(50 to 0) { (position, count), move ->
            val turns = when {
                move < 0 -> (100 - (if (position == 0) 100 else position) - move) / 100
                else -> (position + move) / 100
            }

            val pos = (position + move) % 100
            val corrected = if (pos < 0) pos + 100 else pos

            print("${if (move < 0) "L" else "R"}${abs(move)}")
            println(" -> $position to $corrected ($pos | $turns)")

            corrected to (count + turns)
        }
    }

    println("Last position was $position, count is $count")
}

