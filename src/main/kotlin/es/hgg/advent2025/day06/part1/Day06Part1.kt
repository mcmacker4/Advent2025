package es.hgg.advent2025.day06.part1

import es.hgg.advent2025.common.challengeInput


fun main(): Unit = challengeInput(6) { input ->
    val lines = input
        .lineSequence()
        .map { line ->
            line
                .splitToSequence("\\s+".toRegex())
                .filter { it.isNotEmpty() }
                .toList()
        }
        .toList()

    val operators = lines.last().also { println(it) }

    val initialList = MutableList(operators.size) {
        when (operators[it]) {
            "*" -> 1L
            "+" -> 0L
            else -> throw Exception("Invalid operator '${operators[it]}'")
        }
    }

    lines
        .dropLast(1)
        .map { it.map { value -> value.toLong() } }
        .fold(initialList) { acc, line ->
            println(line)
            line.forEachIndexed { i, value ->
                when (operators[i]) {
                    "*" -> acc[i] *= value
                    "+" -> acc[i] += value
                    else -> throw Exception("Invalid operator '${operators[i]}'")
                }
            }
            acc
        }
        .sum()
        .let { println("Result: $it") }
}