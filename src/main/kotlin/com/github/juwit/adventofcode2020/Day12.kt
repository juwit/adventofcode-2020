package com.github.juwit.adventofcode2020

import kotlin.math.abs

data class Ship(var north: Int = 0, var east: Int = 0, var orientation: Int = 0)

class Day12: Day(12, "Rain Risk") {
    override fun solvePart1(input: List<String>): String {
        val ship = Ship()

        input.forEach {
            var action = it[0]
            val value = it.substring(1).toInt()
            if(action == 'F') {
                // replacing with direction from orientation
                action = when((ship.orientation / 90) % 4){
                    0 -> 'E'
                    1, -3 -> 'N'
                    2, -2 -> 'W'
                    3, -1 -> 'S'
                    else -> throw IllegalArgumentException("unknown orientation : ${ship.orientation}")
                }
            }
            when(action) {
                'N' -> ship.north += value
                'S' -> ship.north -= value
                'E' -> ship.east += value
                'W' -> ship.east -= value
                'L' -> ship.orientation += value
                'R' -> ship.orientation -= value
            }
        }

        // return manhattan distance from 0/0
        return (abs(ship.north) + abs(ship.east)).toString()
    }

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }
}