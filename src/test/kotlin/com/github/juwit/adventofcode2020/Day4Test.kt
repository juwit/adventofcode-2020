package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day4Test {

    val testInput = """
        ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
        byr:1937 iyr:2017 cid:147 hgt:183cm
        
        iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
        hcl:#cfa07d byr:1929
        
        hcl:#ae17e1 iyr:2013
        eyr:2024
        ecl:brn pid:760753108 byr:1931
        hgt:179cm
        
        hcl:#cfa07d eyr:2025 pid:166559648
        iyr:2011 ecl:brn hgt:59in
    """.trimIndent().asStringList()

    @Test
    fun test_parse_passport_line() {
        assertThat(parsePassportLine(testInput[0]))
            .containsAllEntriesOf(
                mapOf(
                    "ecl" to "gry",
                    "pid" to "860033327",
                    "eyr" to "2020",
                    "hcl" to "#fffffd"
                )
            )
    }

    @Test
    fun test_parse_passports() {
        val passports = parsePassports(testInput)

        assertEquals(4, passports.size)
    }

    @Test
    fun part1_should_count_valid_passports() {
        val day = Day4()

        assertEquals("2", day.solvePart1(testInput))
    }

    @Test
    fun part2_should_count_valid_passports() {
        val day = Day4()

        TODO("Not yet implemented")
    }

}