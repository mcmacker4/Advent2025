package es.hgg.advent2025.common

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStream

private object ResourceInput {
    fun stream(path: String): InputStream =
        javaClass.getResourceAsStream("/inputs/$path") ?: throw FileNotFoundException(path)
}

@Suppress("unused")
fun<T> exampleInput(day: Int, block: (BufferedReader) -> T) = ResourceInput.stream("${day.dir}/example").bufferedReader().use(block)
fun<T> challengeInput(day: Int, block: (BufferedReader) -> T) = ResourceInput.stream("${day.dir}/input").bufferedReader().use(block)

private val Int.dir get() = "day${toString().padStart(2, '0')}"
