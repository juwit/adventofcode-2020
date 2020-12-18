package com.github.juwit.adventofcode2020

import java.lang.IllegalStateException

class Day17 : Day(17, "Conway Cubes") {

    class Dimension {

        var currentState = mutableSetOf<Cube>()

        fun nextState() {
            // creating a new dimension value
            val newDimension = mutableSetOf<Cube>()
            // expand the universe !
            val expansion = currentState.flatMap { it.findNewNeighbors(currentState) }.toMutableSet()
            currentState.addAll(expansion)

            currentState.forEach {
                // calculate new states
                newDimension.add(it.nextState(currentState))
            }
            // replacing the dimension !
            currentState = newDimension
        }
    }

    data class Cube(val x: Int, val y: Int, val z: Int) {

        var active: Boolean = false

        fun findNeighbors(currentDimension: MutableSet<Cube>): List<Cube> {
            val neighbors = mutableListOf<Cube>()
            // iterating over cube positions
            for (i in x - 1..x + 1) {
                for (j in y - 1..y + 1) {
                    for (k in z - 1..z + 1) {
                        if (x != i || y != j || z != k) {
                            // find the neighbor in existing dimension ? or create new cube
                            val neighbor = currentDimension.find { it == Cube(i,j,k) } ?: Cube(i,j,k)
                            neighbors.add(neighbor)
                        }
                    }
                }
            }
            return neighbors
        }

        fun findNewNeighbors(currentDimension: MutableSet<Cube>): List<Cube> {
            val newNeighbors = mutableListOf<Cube>()
            // iterating over cube positions
            for (i in x - 1..x + 1) {
                for (j in y - 1..y + 1) {
                    for (k in z - 1..z + 1) {
                        if (x != i || y != j || z != k) {
                            val cube = Cube(i,j,k)
                            if(!currentDimension.contains(cube)) {
                                newNeighbors.add(cube)
                            }
                        }
                    }
                }
            }
            return newNeighbors
        }

        fun nextState(currentDimension: MutableSet<Cube>): Cube {
            if (active) {
                if (findNeighbors(currentDimension).count { it.active } in 2..3) {
                    return this.copy().also {
                        it.active = true
                    }
                } else {
                    return this.copy().also {
                        it.active = false
                    }
                }
            } else if (!active && findNeighbors(currentDimension).count { it.active } == 3) {
                return this.copy().also {
                    it.active = true
                }
            }
            return this.copy()
        }
    }

    fun parseInitialDimensionState(input: List<String>): Dimension {
        val dimension = Dimension()
        // input is Z = 0
        val z = 0
        input.map { it.toCharArray() }.forEachIndexed { y, chars ->
            chars.forEachIndexed { x, c ->
                val active = when (c) {
                    '.' -> false
                    '#' -> true
                    else -> throw IllegalStateException("state is not valid")
                }
                val cube = Cube(x, y, z).also { it.active = active }
                dimension.currentState.add(cube)
            }
        }
        return dimension
    }

    override fun solvePart1(input: List<String>): Number {
        // creating the dimension
        val dimension = parseInitialDimensionState(input)
        // iterating 6 times
        for(i in 1..6){
            dimension.nextState()
        }

        return dimension.currentState.count { it.active }
    }

    override fun solvePart2(input: List<String>): Number {
        TODO("Not yet implemented")
    }


}