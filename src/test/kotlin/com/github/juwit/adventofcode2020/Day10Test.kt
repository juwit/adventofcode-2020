package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day10Test {

    private val testInput = """
        16
        10
        15
        5
        1
        11
        7
        19
        6
        12
        4
    """.trimIndent().asStringList()

    private val secondTestInput = """
        28
        33
        18
        42
        31
        14
        46
        20
        48
        47
        24
        23
        49
        45
        19
        38
        39
        11
        1
        32
        25
        35
        8
        17
        7
        9
        4
        2
        34
        10
        3
    """.trimIndent().asStringList()

    @Test
    fun test_find_joltage_difference_multiplied() {
        assertThat(Day10().solvePart1(testInput)).isEqualTo("35")
        assertThat(Day10().solvePart1(secondTestInput)).isEqualTo("220")
    }

    @Test
    fun test_find_adapters_arrangements() {
        assertThat(Day10().solvePart2(testInput)).isEqualTo("8")
        assertThat(Day10().solvePart2(secondTestInput)).isEqualTo("19208")
    }

}