package es.hgg.advent2025.common

import java.io.FileNotFoundException
import java.io.InputStream


interface Input {
    fun stream(): InputStream
}

fun<T> Input.useLines(block: (Sequence<String>) -> T) = stream().bufferedReader().useLines(block)

sealed class ResourceInput(private val path: String) : Input {
    override fun stream(): InputStream = javaClass.getResourceAsStream("/inputs/$path")
        ?: throw FileNotFoundException()
}

class ExampleInput(day: Int) : ResourceInput("day${day.toString().padStart(2, '0')}/example")
class ChallengeInput(day: Int) : ResourceInput("day${day.toString().padStart(2, '0')}/input")
