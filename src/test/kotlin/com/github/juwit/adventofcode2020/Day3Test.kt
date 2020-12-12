package com.github.juwit.adventofcode2020

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day3Test {

    val testInput = """
        ..##.......
        #...#...#..
        .#....#..#.
        ..#.#...#.#
        .#...##..#.
        ..#.##.....
        .#.#.#....#
        .#........#
        #.##...#...
        #...##....#
        .#..#...#.#
    """.trimIndent().asStringList()

    @Test
    fun land_line_should_tell_if_a_tree_is_present_for_each_line(){
        val slope = 3
        var position = 0

        val hasTree = listOf(false, false, true, false, true, true, false, true, true, true, true)

        testInput.forEachIndexed { index:Int, treeLine:String ->
            assertEquals(hasTree[index], LandLine(treeLine).hasTreeOn(position))
            position += slope
        }
    }

    @Test
    fun part1_should_encountered_tree_count() {
        val day = Day3()

        assertEquals(7, day.solvePart1(testInput))
    }

    @Test
    fun part2_should_encountered_tree_count() {
        val day = Day3()

        assertEquals(336, day.solvePart2(testInput))
    }

}