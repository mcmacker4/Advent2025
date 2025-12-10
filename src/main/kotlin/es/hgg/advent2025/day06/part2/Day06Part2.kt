package es.hgg.advent2025.day06.part2

import es.hgg.advent2025.common.challengeInput


fun main(): Unit = challengeInput(6) { input ->
    val lines = input.readLines()

    val valueLines = lines.dropLast(1).toMutableList()
    val operatorsLine = lines.last()

    val sheetWidth = valueLines.maxOf { it.length }

    val operations = operatorsLine.parseOperators()

    val results = MutableList(operations.size) {
        when (operations[it].operator) {
            '*' -> 1L
            '+' -> 0L
            else -> error("Invalid operator ${operations[it].operator}")
        }
    }

    operations
        .windowed(2, partialWindows = true)
        .forEachIndexed { operationIdx, infos ->
            val info = infos[0]
            val next = infos.getOrNull(1)

            print("[${info.operator}] ")
            print("[${info.pos.toString().padStart(4)}] ")

            val opWidth = (next?.pos ?: (sheetWidth + 1)) - info.pos - 1

            for (column in 0..<opWidth) {
                val number = valueLines.fold(0L) { acc, valueLine ->
                    val ch = valueLine.getOrNull(column + info.pos)
                    if (ch != null && ch.isDigit()) acc * 10 + ch.digitToInt() else acc
                }
                print("$number ")
                when (info.operator) {
                    '*' -> results[operationIdx] *= number
                    '+' -> results[operationIdx] += number
                }
            }

            println("= ${results[operationIdx]}")
        }

    println("Result: ${results.sum()}")

}

private class OpInfo (
    val pos: Int,
    val operator: Char,
)

private fun String.parseOperators(): List<OpInfo> = buildList {
    this@parseOperators.forEachIndexed { i, ch ->
        if (!ch.isWhitespace()) add(OpInfo(i, ch))
    }
}