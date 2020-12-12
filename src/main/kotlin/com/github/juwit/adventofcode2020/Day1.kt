package com.github.juwit.adventofcode2020

class Day1 : Day(1, "Report Repair") {

    override fun solvePart1(input: List<String>): Int {
        val inputAsInt = input.toIntList()
        for (a in inputAsInt) {
            for (b in inputAsInt) {
                if (a + b == 2020) {
                    return a * b
                }
            }
        }

        throw IllegalStateException("Cloud not determine puzzle answer")
    }

    override fun solvePart2(input: List<String>): Int {
        val inputAsInt = input.toIntList()
        for (a in inputAsInt) {
            for (b in inputAsInt) {
                for (c in inputAsInt) {
                    if (a + b + c == 2020) {
                        return a * b * c
                    }
                }
            }
        }

        throw IllegalStateException("Cloud not determine puzzle answer")
    }

}

fun List<String>.toIntList(): List<Int> = this.map(String::toInt)