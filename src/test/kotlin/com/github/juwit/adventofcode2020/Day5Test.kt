package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day5Test {

    private val testInput = """
        FBFBBFFRLR
        BFFFBBFRRR
        FFFBBBFRRR
        BBFFBBFRLL
    """.trimIndent().asStringList()

    @Test
    fun test_parse_boarding_pass() {
        assertThat(testInput[0].toBoardingPass()).isEqualTo(BoardingPass(44, 5))
        assertThat(testInput[1].toBoardingPass()).isEqualTo(BoardingPass(70, 7))
        assertThat(testInput[2].toBoardingPass()).isEqualTo(BoardingPass(14, 7))
        assertThat(testInput[3].toBoardingPass()).isEqualTo(BoardingPass(102, 4))
    }

    @Test
    fun part1_should_find_highest_seat_id() {
        assertThat(Day5().solvePart1(testInput)).isEqualTo("820")
    }

}