package com.github.juwit.adventofcode2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day1Test {

    val testInput = """
        1721
        979
        366
        299
        675
        1456
    """.trimIndent().asStringList()

    @Test
    fun part1_should_find_matching_entries() {
        val day1 = Day1()

        assertEquals("514579", day1.solvePart1(testInput))
    }

    @Test
    fun part2_should_find_matching_entries() {
        val day1 = Day1()

        assertEquals("241861950", day1.solvePart2(testInput))
    }

}