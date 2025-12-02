package es.hgg.advent2025.common

import java.io.FileNotFoundException
import java.io.InputStream
import java.io.InputStreamReader


interface PuzzleInput {
    fun stream(): InputStream
}

fun PuzzleInput.readText(): String {
    val reader = stream().bufferedReader()
    val text = reader.readText()
    reader.close()
    return text
}

fun<T> PuzzleInput.useLines(block: (Sequence<String>) -> T) = stream().bufferedReader().useLines(block)

sealed class ResourceInput(private val path: String) : PuzzleInput {
    override fun stream(): InputStream = javaClass.getResourceAsStream("/inputs/$path")
        ?: throw FileNotFoundException()
}

@Suppress("unused")
class ExampleInput(day: Int) : ResourceInput("day${day.toString().padStart(2, '0')}/example")
class ChallengeInput(day: Int) : ResourceInput("day${day.toString().padStart(2, '0')}/input")
