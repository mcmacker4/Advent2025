package es.hgg.advent2025.day03.part1

import es.hgg.advent2025.common.challengeInput


fun main() {
    val result = challengeInput(3) { reader ->
        reader
            .lineSequence()
            .map(::calculateJoltage)
            .sumOf { it.first * 10 + it.second }
    }

    println("Result: $result")
}

private fun calculateJoltage(bank: String) = bank.map { it.digitToInt() }.withIndex().fold(0 to 0) { top, el ->
    val isLast = el.index == bank.length - 1
    when {
        !isLast && el.value > top.first -> el.value to 0
        !isLast && el.value > top.second -> top.first to el.value
        isLast && el.value > top.second -> top.first to el.value
        else -> top
    }
}