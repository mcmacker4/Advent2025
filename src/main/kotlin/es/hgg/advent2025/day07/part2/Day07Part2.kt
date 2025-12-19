package es.hgg.advent2025.day07.part2

import es.hgg.advent2025.common.challengeInput

private data class Pos(val row: Int, val col: Int)

fun main(): Unit = challengeInput(7) { input ->
    val lines = input.readLines()
    val startCol = lines.first().indexOf('S')

    val totalRows = lines.count { it.contains('^') }
    val startingPos = Pos(0, startCol)

    // from bottom to top
    // splitters create as many paths as the sum of the number of paths created by the reachable splitter on each side
    // or just one if there is no reachable splitter on that side splitters

    val computed = mutableMapOf<Pos, Long>()

    lines
        .asReversed()
        .asSequence()
        .filter { it.contains('^') }
        .forEachIndexed { inverseRow, line ->
            val row = totalRows - 1 - inverseRow

            line
                .asSequence()
                .mapIndexedNotNull { col, char -> col.takeIf { char == '^' } }
                .forEach { col ->

                    val leftPaths = ((row + 1)..<totalRows).firstNotNullOfOrNull {
                        computed[Pos(it, col - 1)]
                    } ?: 1

                    val rightPaths = ((row + 1)..<totalRows).firstNotNullOfOrNull {
                        computed[Pos(it, col + 1)]
                    } ?: 1

                    val pos = Pos(row, col)
                    computed[pos] = (leftPaths + rightPaths).also { println("$pos: $leftPaths + $rightPaths") }

                }

        }

    val pathCount = computed[startingPos]!!
    println("Result at $startingPos: $pathCount")

}

