package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day6Test {

    private val testInput = """
        abc
        
        a
        b
        c
        
        ab
        ac
        
        a
        a
        a
        a
        
        b
    """.trimIndent().asStringList()

    @Test
    fun test_parse_group_answers() {
        assertThat(parseGroupAnswers(testInput)).hasSize(5)

        assertThat(parseGroupAnswers(testInput).map { it.countYesAnswers() })
            .isEqualTo(listOf(3, 3, 3, 1, 1))
    }

    @Test
    fun part1_should_yes_count_sum() {
        assertThat(Day6().solvePart1(testInput)).isEqualTo("11")
    }

}