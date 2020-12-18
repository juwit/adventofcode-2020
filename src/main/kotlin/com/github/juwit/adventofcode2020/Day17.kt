package com.github.juwit.adventofcode2020

import java.lang.IllegalStateException

class Day17 : Day(17, "Conway Cubes") {

    class Dimension {

        var currentState = mutableSetOf<DimensionPart>()

        fun nextState() {
            // creating a new dimension value
            val newDimension = mutableSetOf<DimensionPart>()
            // expand the universe !
            val expansion = currentState.flatMap { it.findNewNeighbors(currentState) }.toMutableSet()
            currentState.addAll(expansion)

            currentState.forEach {
                // calculate new states
                val dimensionPart = it.nextState(currentState)
                if(dimensionPart.active){ // only storing active state
                    newDimension.add(dimensionPart)
                }
            }
            // replacing the dimension !
            currentState = newDimension
        }
    }

    interface DimensionPart {
        var active: Boolean
        fun findNeighbors(currentDimension: MutableSet<DimensionPart>): List<DimensionPart>
        fun findNewNeighbors(currentDimension: MutableSet<DimensionPart>): List<DimensionPart>
        fun nextState(currentDimension: MutableSet<DimensionPart>): DimensionPart
    }

    data class Cube(val x: Int, val y: Int, val z: Int) : DimensionPart {

        override var active: Boolean = false

        override fun findNeighbors(currentDimension: MutableSet<DimensionPart>): List<DimensionPart> {
            val neighbors = mutableListOf<DimensionPart>()
            // iterating over cube positions
            for (i in x - 1..x + 1) {
                for (j in y - 1..y + 1) {
                    for (k in z - 1..z + 1) {
                        if (x != i || y != j || z != k) {
                            // find the neighbor in existing dimension ? or create new cube
                            val neighbor = currentDimension.find { it == Cube(i, j, k) } ?: Cube(i, j, k)
                            neighbors.add(neighbor)
                        }
                    }
                }
            }
            return neighbors
        }

        override fun findNewNeighbors(currentDimension: MutableSet<DimensionPart>): List<Cube> {
            val newNeighbors = mutableListOf<Cube>()
            if(!active){
                // no need to expand for inactive positions ?
                return newNeighbors
            }
            // iterating over cube positions
            for (i in x - 1..x + 1) {
                for (j in y - 1..y + 1) {
                    for (k in z - 1..z + 1) {
                        if (x != i || y != j || z != k) {
                            val cube = Cube(i, j, k)
                            if (!currentDimension.contains(cube)) {
                                newNeighbors.add(cube)
                            }
                        }
                    }
                }
            }
            return newNeighbors
        }

        override fun nextState(currentDimension: MutableSet<DimensionPart>): Cube {
            val activeNeighbors = findNeighbors(currentDimension).count { it.active }
            if (active) {
                if (activeNeighbors in 2..3) {
                    return this.copy().also {
                        it.active = true
                    }
                } else {
                    return this.copy().also {
                        it.active = false
                    }
                }
            } else if (!active && activeNeighbors == 3) {
                return this.copy().also {
                    it.active = true
                }
            }
            return this.copy()
        }
    }

    data class HyperCube(val x: Int, val y: Int, val z: Int, val w: Int) : DimensionPart {

        override var active: Boolean = false

        override fun findNeighbors(currentDimension: MutableSet<DimensionPart>): List<DimensionPart> {
            val neighbors = mutableListOf<DimensionPart>()
            // iterating over cube positions
            for (i in x - 1..x + 1) {
                for (j in y - 1..y + 1) {
                    for (k in z - 1..z + 1) {
                        for (l in w - 1..w + 1) {
                            if (x != i || y != j || z != k || l != w) {
                                // find the neighbor in existing dimension ? or create new cube
                                val neighbor = currentDimension.find { it == HyperCube(i, j, k, l) } ?: HyperCube(i, j, k, l)
                                neighbors.add(neighbor)
                            }
                        }
                    }
                }
            }
            return neighbors
        }

        override fun findNewNeighbors(currentDimension: MutableSet<DimensionPart>): List<HyperCube> {
            val newNeighbors = mutableListOf<HyperCube>()
            if(!active){
                // no need to expand for inactive positions ?
                return newNeighbors
            }
            // iterating over cube positions
            for (i in x - 1..x + 1) {
                for (j in y - 1..y + 1) {
                    for (k in z - 1..z + 1) {
                        for(l in w-1..w+1){
                            if (x != i || y != j || z != k || w != l) {
                                val cube = HyperCube(i, j, k, l)
                                if (!currentDimension.contains(cube)) {
                                    newNeighbors.add(cube)
                                }
                            }
                        }
                    }
                }
            }
            return newNeighbors
        }

        override fun nextState(currentDimension: MutableSet<DimensionPart>): HyperCube {
            val activeNeighbors = findNeighbors(currentDimension).count { it.active }
            if (active) {
                if (activeNeighbors in 2..3) {
                    return this.copy().also {
                        it.active = true
                    }
                } else {
                    return this.copy().also {
                        it.active = false
                    }
                }
            } else if (!active && activeNeighbors == 3) {
                return this.copy().also {
                    it.active = true
                }
            }
            return this.copy()
        }
    }

    fun parseInitial3DimensionState(input: List<String>): Dimension {
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

    fun parseInitial4DimensionState(input: List<String>): Dimension {
        val dimension = Dimension()
        // input is Z = 0, W = 0
        val z = 0
        val w = 0
        input.map { it.toCharArray() }.forEachIndexed { y, chars ->
            chars.forEachIndexed { x, c ->
                val active = when (c) {
                    '.' -> false
                    '#' -> true
                    else -> throw IllegalStateException("state is not valid")
                }
                val cube = HyperCube(x, y, z, w).also { it.active = active }
                dimension.currentState.add(cube)
            }
        }
        return dimension
    }

    override fun solvePart1(input: List<String>): Number {
        // creating the dimension
        val dimension = parseInitial3DimensionState(input)
        // iterating 6 times
        for (i in 1..6) {
            dimension.nextState()
        }

        return dimension.currentState.count { it.active }
    }

    override fun solvePart2(input: List<String>): Number {
        // creating the dimension
        val dimension = parseInitial4DimensionState(input)
        // iterating 6 times
        for (i in 1..6) {
            dimension.nextState()
        }

        return dimension.currentState.count { it.active }
    }


}