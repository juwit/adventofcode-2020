package com.github.juwit.adventofcode2020

data class BoardingPass(val row: Int, val column: Int) {
    fun seatId() = row * 8 + column
}

fun String.toBoardingPass(): BoardingPass {
    // rows & columns are encoded in binary-style format, using F as 0, B as 1, L as 0 & R as 1

    val rowIndex = this.substring(0..6)
        .replace("F", "0")
        .replace("B", "1")
    val colIndex = this.substring(7..9)
        .replace("L", "0")
        .replace("R", "1")

    return BoardingPass(Integer.parseInt(rowIndex, 2), Integer.parseInt(colIndex, 2))
}

class Day5 : Day(5, "Binary Boarding") {
    override fun solvePart1(input: List<String>): String {
        return input.map { it.toBoardingPass() }
            .maxOf { it.seatId() }
            .toString()
    }

    override fun solvePart2(input: List<String>): String {
        return input.map { it.toBoardingPass().seatId() }
                // sort the list of seats
            .sorted()
                // zip the ids two by two
            .zipWithNext()
                // find the ones that don't follow each other (meaning the missing one)
            .find { it.first + 2 == it.second }!!.first.inc().toString()

    }
}