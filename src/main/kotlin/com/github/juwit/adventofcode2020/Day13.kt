package com.github.juwit.adventofcode2020

class Day13: Day(13, "Shuttle Search") {

    override fun solvePart1(input: List<String>): Number {
        val time = input[0].toInt()
        val busIdWithWaitingTime = input[1]
            .split(",")
            .filter { "x" != it }
            .map { it.toInt() }
                // building a pair of bus id with waiting time
            .map { it to it-(time % it) }
            .sortedBy { it.second }
            .first()
        return busIdWithWaitingTime.first * busIdWithWaitingTime.second
    }

    override fun solvePart2(input: List<String>): Number {
        TODO("Not yet implemented")
    }
}