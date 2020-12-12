package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day12Test {

    private val testInput = """
        F10
        N3
        F7
        R90
        F11
    """.trimIndent().asStringList()

    @Test
    fun testSolvePart1(){
        assertThat(Day12().solvePart1(testInput)).isEqualTo(25)
    }

    @Test
    fun testWaypointRotation(){
        assertThat(Waypoint(2,1).rotateLeft(90))
            .isEqualTo(Waypoint(1, -2))
        assertThat(Waypoint(-2,-1).rotateLeft(90))
            .isEqualTo(Waypoint(-1, 2))

        assertThat(Waypoint(4, 10).rotateRight(90))
            .isEqualTo(Waypoint(-10, 4))
        assertThat(Waypoint(2,1).rotateRight(180))
            .isEqualTo(Waypoint(-2, -1))
    }

    @Test
    fun testSolvePart2(){
        assertThat(Day12().solvePart2(testInput)).isEqualTo(286)
    }
}