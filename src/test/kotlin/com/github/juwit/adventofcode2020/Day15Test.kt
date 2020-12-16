package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day15Test {

    @Test
    fun `Turn 4 - The 4th number spoken is 0`() {
        assertThat(Day15().nextNumber(listOf(0, 3, 6))).isEqualTo(0)
    }

    @Test
    fun `Turn 5 - The 5th number spoken is 4 - 1, 3`() {
        assertThat(Day15().nextNumber(listOf(0, 3, 6, 0))).isEqualTo(3)
    }

    @Test
    fun `Turn 6 - The 6th number spoken is 5 - 2, 3`() {
        assertThat(Day15().nextNumber(listOf(0, 3, 6, 0, 3))).isEqualTo(3)
    }

    @Test
    fun `Turn 7 - The 7th number spoken is 1`() {
        assertThat(Day15().nextNumber(listOf(0, 3, 6, 0, 3, 3))).isEqualTo(1)
    }

    @Test
    fun `Turn 8 - The 8th number spoken is 0`() {
        assertThat(Day15().nextNumber(listOf(0, 3, 6, 0, 3, 3, 1))).isEqualTo(0)
    }

    @Test
    fun `Turn 9 - The 9th number spoken is 4`() {
        assertThat(Day15().nextNumber(listOf(0, 3, 6, 0, 3, 3, 1, 0))).isEqualTo(4)
    }

    @Test
    fun `Turn 10 - The 10th number spoken is 0`() {
        assertThat(Day15().nextNumber(listOf(0, 3, 6, 0, 3, 3, 1, 0, 4))).isEqualTo(0)
    }

    @Test
    fun testSolvePart1() {
        assertThat(Day15().solvePart1(listOf("0,3,6"))).isEqualTo(436L)
        assertThat(Day15().solvePart1(listOf("1,3,2"))).isEqualTo(1L)
        assertThat(Day15().solvePart1(listOf("2,1,3"))).isEqualTo(10L)
        assertThat(Day15().solvePart1(listOf("1,2,3"))).isEqualTo(27L)
        assertThat(Day15().solvePart1(listOf("2,3,1"))).isEqualTo(78L)
        assertThat(Day15().solvePart1(listOf("3,2,1"))).isEqualTo(438L)
        assertThat(Day15().solvePart1(listOf("3,1,2"))).isEqualTo(1836L)
    }

    @Test
    fun testSolvePart2() {
        assertThat(Day15().solvePart2(listOf("0,3,6"))).isEqualTo(175594L)
        assertThat(Day15().solvePart2(listOf("1,3,2"))).isEqualTo(2578L)
        assertThat(Day15().solvePart2(listOf("2,1,3"))).isEqualTo(3544142L)
        assertThat(Day15().solvePart2(listOf("1,2,3"))).isEqualTo(261214L)
        assertThat(Day15().solvePart2(listOf("2,3,1"))).isEqualTo(6895259L)
        assertThat(Day15().solvePart2(listOf("3,2,1"))).isEqualTo(18L)
        assertThat(Day15().solvePart2(listOf("3,1,2"))).isEqualTo(362L)
    }

}