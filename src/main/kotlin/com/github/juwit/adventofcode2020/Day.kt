package com.github.juwit.adventofcode2020

import kotlin.system.measureTimeMillis

abstract class Day(val id: Int, private val title: String) {

    fun inputFileName() = "/input-day-${id.toString().padStart(2, '0')}.txt"

    fun loadInput(): List<String> = inputFileName().asString().asStringList()

    private val input by lazy { loadInput() }

    fun solveDay() {
        println("--- Day ${this.id}: ${this.title} ---")
        var part1Solution: Number
        val part1Time = measureTimeMillis {
            part1Solution = this.solvePart1(input)
        }
        println("Puzzle answer : $part1Solution - time : $part1Time ms")

        println("--- Part Two ---")
        var part2Solution: Number
        val part2Time = measureTimeMillis {
            part2Solution = this.solvePart2(input)
        }
        println("Puzzle answer : $part2Solution - time $part2Time ms")

        println()
    }

    abstract fun solvePart1(input: List<String>): Number

    abstract fun solvePart2(input: List<String>): Number

}

/**
 * Gets a file content from its name
 */
fun String.asString(): String = Day::class.java.getResource(this).readText()

/**
 * Splits a new-line separated String in List<String>
 */
fun String.asStringList(): List<String> = this.split("\n")
