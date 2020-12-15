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

        assertThat(11L.applyMask(mask)).isEqualTo("000000000000000000000000000001001001")
        assertThat(101L.applyMask(mask)).isEqualTo("000000000000000000000000000001100101")
        assertThat(0L.applyMask(mask)).isEqualTo("000000000000000000000000000001000000")
    }

    @Test
    fun test_apply_mask_version_2(){
        val mask = "000000000000000000000000000000X1001X"

        assertThat(42L.applyMaskVersion2(mask)).isEqualTo(listOf(
            "000000000000000000000000000000011010",
            "000000000000000000000000000000011011",
            "000000000000000000000000000000111010",
            "000000000000000000000000000000111011"
        ))
    }

    @Test
    fun testSolvePart2(){
        val programV2 = """
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
        """.trimIndent().asStringList()
        assertThat(Day14().solvePart2(programV2)).isEqualTo(208L)
    }

}