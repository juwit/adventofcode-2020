package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day14Test {

    private val program = """
        mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
        mem[8] = 11
        mem[7] = 101
        mem[8] = 0
    """.trimIndent().asStringList()

    @Test
    fun testSolvePart1(){
        assertThat(Day14().solvePart1(program)).isEqualTo(165L)
    }

    @Test
    fun test_convert_decimal_to_binary(){
        assertThat(11L.toBinaryString()).isEqualTo("000000000000000000000000000000001011")
    }

    @Test
    fun test_apply_mask(){
        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"

        assertThat(11L.applyMask(mask)).isEqualTo(73)
        assertThat(101L.applyMask(mask)).isEqualTo(101)
        assertThat(0L.applyMask(mask)).isEqualTo(64)
    }

}