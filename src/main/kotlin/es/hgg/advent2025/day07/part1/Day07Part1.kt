package es.hgg.advent2025.day07.part1

import es.hgg.advent2025.common.challengeInput


fun main() = challengeInput(7) { input ->
    val lines = input.readLines()

    val (width, start) = lines.first().let {
        it.length to it.indexOf('S')
    }

    val rays = mutableSetOf<Int>()
    rays.add(start)

    var count = 0

    lines
        .asSequence()
        .drop(1)
        .forEach { line ->
            line
                .asSequence()
                .mapIndexed(::Pair)
                .filter { it.second == '^' }
                .forEach { (idx, _) ->
                    if (idx in rays) {
                        if (idx > 0) rays.add(idx - 1)
                        if (idx < width - 1) rays.add(idx + 1)
                        rays.remove(idx)
                        count++
                    }
                }
        }

    println("Count: $count")

}