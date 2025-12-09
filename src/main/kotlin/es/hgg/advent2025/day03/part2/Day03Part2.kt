package es.hgg.advent2025.day03.part2

import es.hgg.advent2025.common.challengeInput


const val BATTERY_COUNT = 12

fun main() {
    val result = challengeInput(3) { reader ->
        reader
            .lineSequence()
            .map(::calculateJoltage)
            .sum()
    }

    println("FINAL: $result")
}

private fun calculateJoltage(bank: String): Long {
    val batteries = IntArray(BATTERY_COUNT)

    bank.forEachIndexed { idx, bat ->
        val battery = bat.digitToInt()

        batteries
            .indices
            .reversed()
            .firstOrNull {
                batteries[it] < battery && (it == 0 || batteries[it - 1] >= battery || bank.length - idx == BATTERY_COUNT - it)
            }?.let {
                batteries[it] = battery
                if (it < BATTERY_COUNT - 1) batteries.fill(0, fromIndex = it + 1)
            }
    }

    return batteries.fold(0L) { acc, i -> acc * 10 + i }.also { println("Result $it\n") }
}