package com.github.juwit.adventofcode2020

import com.github.juwit.adventofcode2020.day01.Day1
import kotlin.system.measureTimeMillis

fun main() {
    println("[AdventOfCode - 2020]")
    println()

    val day1 = Day1()
    day1.solveDay()
}

abstract class Day(val id: Int, val title: String){

    fun solveDay() {
        println("--- Day ${this.id}: ${this.title} ---")
        var part1Solution: String
        val part1Time = measureTimeMillis {
            part1Solution = this.solvePart1()
        }
        println("Puzzle answer : $part1Solution - time : $part1Time ms")

        println("--- Part Two ---")
        var part2Solution: String
        val part2Time = measureTimeMillis {
            part2Solution = this.solvePart2()
        }
        println("Puzzle answer : $part2Solution - time $part2Time ms")

        println()
    }

    abstract fun solvePart1(): String

    abstract fun solvePart2(): String
}