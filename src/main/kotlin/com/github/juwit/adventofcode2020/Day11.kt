package com.github.juwit.adventofcode2020

import java.lang.Integer.max
import java.lang.Integer.min

fun nextState(occupied: Boolean?, adjacent: List<Boolean>, tolerance: Int = 4): Boolean? {
    if (occupied == false) {
        return adjacent.all { it == false }
    }
    if (occupied == true && adjacent.count { it == true } >= tolerance) {
        return false
    }
    return occupied
}

fun oneRound(
    waitingArea: List<List<Boolean?>>,
    adjacencyFunction: (List<List<Boolean?>>, Int, Int) -> List<Boolean>,
    tolerance: Int
): List<List<Boolean?>> {
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

            val adjacentSeats = adjacencyFunction(waitingArea, column, row)

            newWaitingAreaState[column][row] = nextState(currentSeat, adjacentSeats, tolerance)
        }
    }

    return newWaitingAreaState
}

fun adjacentSeats(waitingArea: List<List<Boolean?>>, column: Int, row: Int): List<Boolean> {
    val numColumns = waitingArea.size
    val numRows = waitingArea[0].size

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
    return adjacentSeats.filterNotNull()
}

fun outOfBounds(position:Pair<Int,Int>, numColumns: Int, numRows: Int): Boolean {
    val (column, row) = position
    return column < 0 || column > numColumns - 1 || row < 0 || row > numRows - 1
}

// functions to get next position to inspect for visibility
fun up(position:Pair<Int,Int>) = position.first to position.second -1
fun down(position:Pair<Int,Int>) = position.first to position.second + 1
fun left(position:Pair<Int,Int>) = position.first - 1 to position.second
fun right(position:Pair<Int,Int>) = position.first + 1 to position.second
fun upLeft(position:Pair<Int,Int>) = up(left(position))
fun upRight(position:Pair<Int,Int>) = up(right(position))
fun downLeft(position:Pair<Int,Int>) = down(left(position))
fun downRight(position:Pair<Int,Int>) = down(right(position))

fun visibleSeats(waitingArea: List<List<Boolean?>>, column: Int, row: Int): List<Boolean> {
    val numColumns = waitingArea.size
    val numRows = waitingArea[0].size

    val visibleSeats = mutableListOf<Boolean?>()

    val directions = listOf(::up, ::left, ::down, ::right, ::upLeft, ::upRight, ::downLeft, ::downRight)

    directions.forEach { directionFunction ->
        // find first visible seat
        val currentPosition = column to row
        var nextPosition = directionFunction(currentPosition)
        while( ! outOfBounds(nextPosition, numColumns, numRows) && waitingArea[nextPosition.first][nextPosition.second] == null ){
            nextPosition = directionFunction(nextPosition)
        }
        if(! outOfBounds(nextPosition, numColumns, numRows)){
            visibleSeats.add(waitingArea[nextPosition.first][nextPosition.second])
        }
    }

    return visibleSeats.filterNotNull()
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

    override fun solvePart1(input: List<String>): Int {
        // parse input
        var waitingArea: List<List<Boolean?>> = parseInput(input)

        var previousWaitingAreaState:List<List<Boolean?>>? = null

        // find end of chaos
        while(waitingArea != previousWaitingAreaState) {
            previousWaitingAreaState = waitingArea
            waitingArea = oneRound(waitingArea, ::adjacentSeats, 4)
        }
        return waitingArea.sumOf { it.count { b -> b == true } }
    }

    override fun solvePart2(input: List<String>): Int {
        // parse input
        var waitingArea: List<List<Boolean?>> = parseInput(input)

        var previousWaitingAreaState:List<List<Boolean?>>? = null

        // find end of chaos
        while(waitingArea != previousWaitingAreaState) {
            previousWaitingAreaState = waitingArea
            waitingArea = oneRound(waitingArea, ::visibleSeats, 5)
        }
        return waitingArea.sumOf { it.count { b -> b == true } }
    }

}