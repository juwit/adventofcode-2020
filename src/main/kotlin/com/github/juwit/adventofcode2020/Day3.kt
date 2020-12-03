package com.github.juwit.adventofcode2020

data class LandLine(val line: String){
    fun hasTreeOn(i: Int): Boolean = line[i % line.length] == '#'
}

class Day3: Day(3, "Toboggan Trajectory") {

    override fun solvePart1(input: List<String>): String {
        val slope = 3
        var position = -slope
        return input.map(::LandLine).count {
            position += 3 // yeeeeeeeee
            it.hasTreeOn(position)
        }.toString()
    }

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }

}