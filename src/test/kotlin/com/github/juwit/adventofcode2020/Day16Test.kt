package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day16Test {

    val testInput = """
        class: 1-3 or 5-7
        row: 6-11 or 33-44
        seat: 13-40 or 45-50

        your ticket:
        7,1,14

        nearby tickets:
        7,3,47
        40,4,50
        55,2,20
        38,6,12
    """.trimIndent().asStringList()

    @Test
    fun `parse fields rules`() {
        assertThat(Day16().parseFieldsRules(testInput).map { it.validRanges }).isEqualTo(
            listOf(
                listOf(1..3, 5..7),
                listOf(6..11, 33..44),
                listOf(13..40, 45..50)
            )
        )
    }

    @Test
    fun `parse nearby tickets`() {
        assertThat(Day16().parseNearbyTickets(testInput)).isEqualTo(
            listOf(
                listOf(7, 3, 47),
                listOf(40, 4, 50),
                listOf(55, 2, 20),
                listOf(38, 6, 12),
            )
        )
    }

    @Test
    fun testPart1() {
        assertThat(Day16().solvePart1(testInput)).isEqualTo(71)
    }

}