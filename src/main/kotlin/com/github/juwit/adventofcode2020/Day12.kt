package com.github.juwit.adventofcode2020

import kotlin.math.abs

data class Ship(var north: Int = 0, var east: Int = 0, var orientation: Int = 0) {
    fun moveTo(waypoint: Waypoint, value: Int) {
        this.north += waypoint.north * value
        this.east += waypoint.east * value
    }
}

data class Waypoint(var north: Int = 1, var east: Int = 10) {
    fun rotateLeft(degrees: Int): Waypoint {
        for(i in 0..(degrees/90)-1){
            val newNorth = east
            val newEast = -north
            north = newNorth
            east = newEast
        }
        return this
    }

    fun rotateRight(degrees: Int): Waypoint {
        for(i in 0..(degrees/90)-1){
            val newNorth = -east
            val newEast = north
            north = newNorth
            east = newEast
        }
        return this
    }
}

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
        val ship = Ship()
        val waypoint = Waypoint()

        input.forEach {
            val action = it[0]
            val value = it.substring(1).toInt()
            when(action) {
                'N' -> waypoint.north += value
                'S' -> waypoint.north -= value
                'E' -> waypoint.east += value
                'W' -> waypoint.east -= value
                'L' -> waypoint.rotateLeft(value)
                'R' -> waypoint.rotateRight(value)
                'F' -> ship.moveTo(waypoint, value)
            }
        }

        // return manhattan distance from 0/0
        return (abs(ship.north) + abs(ship.east)).toString()
    }
}