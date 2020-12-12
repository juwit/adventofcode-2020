package com.github.juwit.adventofcode2020

data class LandLine(val line: String){
    fun hasTreeOn(position: Int): Boolean = line[position % line.length] == '#'
}

data class Slope(val down:Int, val right: Int)

class Day3: Day(3, "Toboggan Trajectory") {

    fun countTreesForSlope(input: List<String>, slope: Slope): Int {
        val landlines = input.map(::LandLine)

        var position = 0
        var count = 0

        for (line in landlines.indices step slope.down) {
            if(landlines[line].hasTreeOn(position)){
                count++
            }
            position += slope.right
        }
        return count
    }

    override fun solvePart1(input: List<String>): Int {
        val slope = Slope(1,3)

        return countTreesForSlope(input, slope)
    }

    override fun solvePart2(input: List<String>): Int {
        val slope1 = Slope(1,1)
        val slope2 = Slope(1,3)
        val slope3 = Slope(1,5)
        val slope4 = Slope(1,7)
        val slope5 = Slope(2,1)

        return listOf(slope1, slope2, slope3, slope4, slope5)
                .map { countTreesForSlope(input, it) }
                .reduce { a, b -> a*b }
    }

}