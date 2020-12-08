package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day8Test {

    private val testInput = """
        nop +0
        acc +1
        jmp +4
        acc +3
        jmp -3
        acc -99
        acc +1
        jmp -4
        acc +6
    """.trimIndent().asStringList()


    @Test
    fun test_parse_program_instructions(){
        assertThat(parseProgramInstructions(testInput)).isEqualTo(
            listOf(
                NoOpInstruction(0),
                AccInstruction(1),
                JumpInstruction(4),
                AccInstruction(3),
                JumpInstruction(-3),
                AccInstruction(-99),
                AccInstruction(1),
                JumpInstruction(-4),
                AccInstruction(6)
            ))
    }

    @Test
    fun test_find_accumulator_value_before_loop(){
        assertThat(Day8().solvePart1(testInput)).isEqualTo("5")
    }

    @Test
    fun test_try_to_correct_loop(){
        assertThat(Day8().solvePart2(testInput)).isEqualTo("8")
    }

}