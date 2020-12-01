package com.github.juwit.adventofcode2020

import com.github.juwit.adventofcode2020.day01.Day1

fun main() {
    println("[AdventOfCode - 2020]")
    println()

    val day1 = Day1()
    day1.solveDay()
}

abstract class Day(val id: Int, val title: String){

    fun solveDay() {
        println("--- Day ${this.id}: ${this.title} ---")
        val part1Solution = this.solvePart1()
        println("Puzzle answer : $part1Solution")
        val part2Solution = this.solvePart2()
        println("--- Part Two ---")
        println("Puzzle answer : $part2Solution")
        println()
    }

    abstract fun solvePart1(): String

    abstract fun solvePart2(): String
}