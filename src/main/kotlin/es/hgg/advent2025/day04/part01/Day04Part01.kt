package es.hgg.advent2025.day04.part01

import es.hgg.advent2025.common.ChallengeInput
import es.hgg.advent2025.common.useLines

private data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}

private object Kernel {

    const val SIZE = 1

    private val points: List<Point>

    init {
        val points = mutableListOf<Point>()
        for (x in -SIZE..SIZE) {
            for (y in -SIZE..SIZE) {
                points.add(Point(x, y))
            }
        }
        this.points = points
    }

    fun at(center: Point) = points.asSequence().map { center + it }
    fun surrounding(center: Point) = at(center).filter { it != center }

}

private class Map(val lines: List<String>) {
    val width = lines[0].length
    val height = lines.size

    operator fun get(point: Point) =
        (point.x in 0..<width) && (point.y in 0..<height) && (lines[point.y][point.x] == '@')
}

fun main() {

    ChallengeInput(4).useLines { seq ->
        val map = Map(seq.toList())

        var count = 0

        for (y in 0..<map.height) {
            for (x in 0..<map.width) {
                val pos = Point(x, y)
                if (map[pos] && Kernel.surrounding(pos).count { map[it] } < 4)
                    count++
            }
        }

        println("Result: $count")
    }

}
