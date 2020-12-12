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
        assertThat(Day12().solvePart1(testInput)).isEqualTo("25")
    }
}