package com.github.juwit.adventofcode2020

import java.lang.IllegalArgumentException

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

    fun checkIfTimestampIsRight(timestamp: Long, busSpec: String) : Boolean{
        return busSpec.split(",")
            .mapIndexed { index, s -> s to index }
            .filter { it.first != "x" }
            .map { it.first.toInt() to it.second}
            .map { (timestamp + it.second ) % it.first }
            .all { it.toInt() == 0 }
    }

    fun checkIfTimestampIsRight(timestamp: Long, busSpecs: List<Pair<Int,Int>>) : Boolean{
        return busSpecs
            .map { (timestamp + it.second ) % it.first }
            .all { it.toInt() == 0 }
    }

    override fun solvePart2(input: List<String>): Number {
        // try but brute forcing
        // with an increment of the least-common-multiple ?
        val busSpecs = input[1]
            .split(",")
            .mapIndexed { index, s -> s to index }
            .filter { it.first != "x" }
            .map { it.first.toInt() to it.second}

        // try to find when first busses align

        var timestamp = 0L // start at 0
        var increment = 1L // start with a 1 increment
        busSpecs.forEachIndexed { index, busSpec ->
            val (busId) = busSpec

            // try to find if sub-spec is valid
            while( ! checkIfTimestampIsRight(timestamp, busSpecs.subList(0, index+1))){
                timestamp += increment

            }
            // found new value that matches, find other timestamps that matches
            increment *= busId
        }

        if(checkIfTimestampIsRight(timestamp, input[1])){
            // found !
            return timestamp
        }
        else {
            throw Exception("could not find puzzle answer")
        }

        return timestamp
    }
}