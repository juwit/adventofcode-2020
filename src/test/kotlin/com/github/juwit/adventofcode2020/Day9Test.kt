package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day9Test {

    private val testInput = """
        35
        20
        15
        25
        47
        40
        62
        55
        65
        95
        102
        117
        150
        182
        127
        219
        299
        277
        309
        576
    """.trimIndent().asStringList()

    @Test
    fun test_find_if_position_is_right() {
        val input = testInput.map(String::toLong)

        assertThat(isPositionRight(5, input, 5)).isTrue
        assertThat(isPositionRight(6, input, 5)).isTrue
        assertThat(isPositionRight(7, input, 5)).isTrue
        assertThat(isPositionRight(8, input, 5)).isTrue
        assertThat(isPositionRight(9, input, 5)).isTrue
        assertThat(isPositionRight(10, input, 5)).isTrue
        assertThat(isPositionRight(11, input, 5)).isTrue
        assertThat(isPositionRight(12, input, 5)).isTrue
        assertThat(isPositionRight(13, input, 5)).isTrue
        assertThat(isPositionRight(14, input, 5)).isFalse
    }

    @Test
    fun test_find_first_wrong_value() {
        assertThat(Day9(5).solvePart1(testInput)).isEqualTo("127")
    }

    @Test
    fun test_find_contiguous_set() {
        assertThat(Day9(5).solvePart2(testInput)).isEqualTo("62")
    }

}