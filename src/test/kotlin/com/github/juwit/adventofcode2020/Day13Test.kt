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

}