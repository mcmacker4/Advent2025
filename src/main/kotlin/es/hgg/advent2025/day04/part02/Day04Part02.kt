package es.hgg.advent2025.day04.part02

import es.hgg.advent2025.common.ChallengeInput
import es.hgg.advent2025.common.useLines


private data class Point(val x: Int, val y: Int) {
    operator fun plus(o: Point) = Point(x + o.x, y + o.y)
    operator fun minus(o: Point) = Point(x - o.x, y - o.y)
}

private class World(val width: Int, val height: Int) {
    private val cells = BooleanArray(width * height)

    operator fun get(point: Point) = cells[point.idx]
    operator fun set(point: Point, value: Boolean) { cells[point.idx] = value }

    fun cells(): Sequence<Point> = cells.indices.asSequence().map { it.point }

    fun surrounding(center: Point): Sequence<Point> =
        (-1..1).asSequence().flatMap { x -> (-1..1).asSequence().map { y -> Point(x, y) + center } }
            .filter { it.inBounds && it != center }

    private val Int.point get() = Point(this % width, this / width)
    private val Point.idx get() = y * width + x

    private val Point.inBounds get() = x in 0..<width && y in 0..<height

    fun print() {
        for (y in 0..<height) {
            for (x in 0..<width) {
                print(if (get(Point(x, y))) '@' else '.')
            }
            println()
        }
    }
}

private fun World.fill(lines: List<String>) {
    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, ch ->
            this[Point(x, y)] = ch == '@'
        }
    }
}

private fun World(lines: List<String>) =
    World(lines[0].length, lines.size).apply { fill(lines) }

fun main() {
    val lines = ChallengeInput(4).useLines { it.toList() }
    val world = World(lines)

    var total = 0

    do {
        var removed = 0
        world.cells().forEach { pos ->
            if (world[pos]) {
                val surroundingRolls = world.surrounding(pos).count { world[it] }
                if (surroundingRolls < 4) {
                    world[pos] = false
                    removed++
                }
            }
        }
        total += removed
    } while (removed > 0)

    world.print()

    println("Total removed: $total")
}


