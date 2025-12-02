package es.hgg.advent2025.day01

import es.hgg.advent2025.common.Challenge
import es.hgg.advent2025.common.ChallengeInput
import es.hgg.advent2025.common.Input


class Day01Part01(input: Input = ChallengeInput("01")) : Challenge(input) {

    override fun run(input: Sequence<String>) {
        val (position, count) = input.map { line ->
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

        println("Last position was $position, count is $count")
    }

}

fun main() {
    Day01Part01().run()
}