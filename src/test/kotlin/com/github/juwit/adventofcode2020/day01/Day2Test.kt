package com.github.juwit.adventofcode2020.day01

import com.github.juwit.adventofcode2020.Day2
import com.github.juwit.adventofcode2020.asStringList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day2Test{

    val testInput = """
        1-3 a: abcde
        1-3 b: cdefg
        2-9 c: ccccccccc
    """.trimIndent().asStringList()

    @Test
    fun part1_should_find_valid_passwords(){
        val day = Day2()

        assertEquals("2", day.solvePart1(testInput))
    }

    @Test
    fun part2_should_find_valid_passwords(){
        val day = Day2()

        assertEquals("1", day.solvePart2(testInput))
    }

}