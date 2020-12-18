package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day17Test {

    val testInput = """
        .#.
        ..#
        ###
    """.trimIndent().asStringList()

    @Test
    fun `Each cube only ever considers its neighbors - any of the 26 other cubes where any of their coordinates differ by at most 1`() {
        assertThat(Day17.Cube(1, 2, 3).findNeighbors(mutableSetOf())).contains(
            Day17.Cube(0, 1, 2),
            Day17.Cube(0, 2, 2),
            Day17.Cube(0, 3, 2),
            Day17.Cube(1, 1, 2),
            Day17.Cube(1, 2, 2),
            Day17.Cube(1, 3, 2),
            Day17.Cube(2, 1, 2),
            Day17.Cube(2, 2, 2),
            Day17.Cube(2, 3, 2),

            Day17.Cube(0, 1, 3),
            Day17.Cube(0, 2, 3),
            Day17.Cube(0, 3, 3),
            Day17.Cube(1, 1, 3),
//            Day17.Cube(1,2,3),
            Day17.Cube(1, 3, 3),
            Day17.Cube(2, 1, 3),
            Day17.Cube(2, 2, 3),
            Day17.Cube(2, 3, 3),

            Day17.Cube(0, 1, 4),
            Day17.Cube(0, 2, 4),
            Day17.Cube(0, 3, 4),
            Day17.Cube(1, 1, 4),
            Day17.Cube(1, 2, 4),
            Day17.Cube(1, 3, 4),
            Day17.Cube(2, 1, 4),
            Day17.Cube(2, 2, 4),
            Day17.Cube(2, 3, 4),
        )
    }

    @Test
    fun testParseInitialDimensionState() {
        assertThat(Day17().parseInitial3DimensionState(testInput).currentState)
            .contains(
                Day17.Cube(0, 0, 0),
                Day17.Cube(1, 0, 0),
                Day17.Cube(2, 0, 0),
                Day17.Cube(0, 1, 0),
                Day17.Cube(1, 1, 0),
                Day17.Cube(2, 1, 0),
                Day17.Cube(0, 2, 0),
                Day17.Cube(1, 2, 0),
                Day17.Cube(2, 2, 0),
            )
    }

    @Test
    fun testAfterOneCycle(){
        val dimension = Day17().parseInitial3DimensionState(testInput)
        dimension.nextState()

        val after1Cycle = dimension.currentState

        val activeCubes = setOf(
            Day17.Cube(0, 1, -1),
            Day17.Cube(1,3,-1),
            Day17.Cube(2,2,-1),

            Day17.Cube(0,1,0),
            Day17.Cube(1,2,0),
            Day17.Cube(1,3,0),
            Day17.Cube(2,1,0),
            Day17.Cube(2,2,0),

            Day17.Cube(0, 1, 1),
            Day17.Cube(1,3,1),
            Day17.Cube(2,2,1),
        )

        assertThat(after1Cycle.filter { it.active }.toSet()).isEqualTo(activeCubes)
    }

    @Test
    fun testSolvePart1(){
        assertThat(Day17().solvePart1(testInput)).isEqualTo(112)
    }

    @Test
    fun testSolvePart2(){
        assertThat(Day17().solvePart2(testInput)).isEqualTo(848)
    }
}