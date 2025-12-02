package es.hgg.advent2025.common


interface Input {
    fun<T> readLines(callback: (Sequence<String>) -> T): T
}

abstract class ResourceInput(private val path: String) : Input {
    private fun stream() = javaClass.getResourceAsStream("/inputs/$path")
    override fun<T> readLines(callback: (Sequence<String>) -> T): T = stream()!!.bufferedReader().useLines(callback)
}

class ExampleInput(day: String) : ResourceInput("day$day/example")
class ChallengeInput(day: String) : ResourceInput("day$day/input")
