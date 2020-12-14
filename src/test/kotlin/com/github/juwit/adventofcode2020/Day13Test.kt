package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day13Test {

    private val testInput = """
        939
        7,13,x,x,59,x,31,19
    """.trimIndent().asStringList()

    @Test
    fun testSolvePart1(){
        assertThat(Day13().solvePart1(testInput)).isEqualTo(295)
    }

    @Test
    fun testCheckFunction(){
        assertThat(Day13().checkIfTimestampIsRight(1068781, testInput[1])).isTrue

        assertThat(Day13().checkIfTimestampIsRight(3417, "17,x,13,19")).isTrue
        assertThat(Day13().checkIfTimestampIsRight(754018, "67,7,59,61")).isTrue
        assertThat(Day13().checkIfTimestampIsRight(779210, "67,x,7,59,61")).isTrue
        assertThat(Day13().checkIfTimestampIsRight(1261476, "67,7,x,59,61")).isTrue
        assertThat(Day13().checkIfTimestampIsRight(1202161486, "1789,37,47,1889")).isTrue
    }

    @Test
    fun testSolvePart2(){
        assertThat(Day13().solvePart2(testInput)).isEqualTo(1068781L)

        assertThat(Day13().solvePart2(listOf("","17,x,13,19"))).isEqualTo(3417L)
        assertThat(Day13().solvePart2(listOf("","67,7,59,61"))).isEqualTo(754018L)
        assertThat(Day13().solvePart2(listOf("","67,x,7,59,61"))).isEqualTo(779210L)
        assertThat(Day13().solvePart2(listOf("","67,7,x,59,61"))).isEqualTo(1261476L)
        assertThat(Day13().solvePart2(listOf("","1789,37,47,1889"))).isEqualTo(1202161486L)

    }

}