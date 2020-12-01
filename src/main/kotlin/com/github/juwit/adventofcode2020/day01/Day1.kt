package com.github.juwit.adventofcode2020.day01

import com.github.juwit.adventofcode2020.Day

class Day1: Day(1, "Report Repair"){

    val input = "/input-day-01.txt".asIntList()

    override fun solvePart1(): String {
        for (a in input) {
            for(b in input) {
                if(a+b == 2020){
                    return (a*b).toString()
                }
            }
        }

        throw IllegalStateException("Cloud not determine puzzle answer")
    }

    override fun solvePart2(): String {
        for (a in input) {
            for(b in input) {
                for(c in input) {
                    if(a+b+c == 2020){
                        return (a*b*c).toString()
                    }
                }
            }
        }

        throw IllegalStateException("Cloud not determine puzzle answer")
    }

}

fun String.asString(): String = {}.javaClass.getResource(this).readText()

fun String.asStringList(): List<String> = this.asString().split("\n")

fun String.asIntList(): List<Int> = this.asStringList().map { it.toInt() }