package com.github.juwit.adventofcode2020

import java.lang.Integer.max
import java.lang.Integer.min

fun nextState(occupied: Boolean?, adjacent: List<Boolean?>): Boolean? {
    if (occupied == false) {
        return adjacent.filterNotNull().all { it == false }
    }
    if (occupied == true && adjacent.filterNotNull().count { it == true } >= 4) {
        return false
    }
    return occupied
}

fun oneRound(waitingArea: List<List<Boolean?>>): List<List<Boolean?>> {
    val numColumns = waitingArea.size
    val numRows = waitingArea[0].size

    // create new array
    val newWaitingAreaState = mutableListOf<MutableList<Boolean?>>()
    // init all with null
    for (column in waitingArea.indices) {
        newWaitingAreaState.add(mutableListOf())
        for (row in waitingArea[column].indices) {
            newWaitingAreaState[column].add(null)
        }
    }

    // calculating new state
    for (column in waitingArea.indices) {
        for (row in waitingArea[column].indices) {
            val currentSeat = waitingArea[column][row]

            val adjacentSeats = mutableListOf<Boolean?>()

            val colIndices = max(0, column-1)..min(numColumns-1, column+1)
            val rowIndices = max(0, row - 1)..min(numRows-1, row+1)
            for(x in colIndices){
                for(y in rowIndices){
                    if(! (x == column && y == row)){
                        adjacentSeats.add(waitingArea[x][y])
                    }
                }
            }

            newWaitingAreaState[column][row] = nextState(currentSeat, adjacentSeats)
        }
    }

    return newWaitingAreaState
}

fun Boolean?.toSeat() = when (this) {
    true -> "#"
    false -> "L"
    else -> "."
}

fun String.toSeatState() = when (this) {
    "L" -> false
    "#" -> true
    else -> null
}

fun parseInput(input: List<String>) = input.map { it.toCharArray().map { it.toString() } }.toWaitingAreaBoolean()

fun List<Boolean?>.toWaitingAreaStringList() = this.map { it.toSeat() }.joinToString(separator = "")

fun List<List<Boolean?>>.toWaitingAreaString() = this.map { it.toWaitingAreaStringList() }

fun List<String>.toWaitingAreaBooleanList() = this.map { it.toSeatState() }

fun List<List<String>>.toWaitingAreaBoolean() = this.map { it.toWaitingAreaBooleanList() }



class Day11 : Day(11, "Seating System") {

    override fun solvePart1(input: List<String>): String {
        // parse input
        var waitingArea: List<List<Boolean?>> = parseInput(input)

        var previousWaitingAreaState:List<List<Boolean?>>? = null

        // find end of chaos
        while(waitingArea != previousWaitingAreaState) {
            previousWaitingAreaState = waitingArea
            waitingArea = oneRound(waitingArea)
        }
        return waitingArea.sumOf { it.count { b -> b == true } }.toString()
    }

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }

}