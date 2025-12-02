package es.hgg.advent2025.common

abstract class Challenge(private val input: Input) {
    abstract fun run(input: Sequence<String>)
    fun run() = input.readLines(::run)
}