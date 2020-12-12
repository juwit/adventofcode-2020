package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class Day7Test {

    private val testInput = """
        light red bags contain 1 bright white bag, 2 muted yellow bags.
        dark orange bags contain 3 bright white bags, 4 muted yellow bags.
        bright white bags contain 1 shiny gold bag.
        muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
        shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
        faded blue bags contain no other bags.
        dotted black bags contain no other bags.
    """.trimIndent().asStringList()

    @Test
    fun test_parse_bags_graph() {
        val bagsGraph = parseBagsGraph(testInput)

        assertThat(bagsGraph).hasSize(9)

        assertThat(bagsGraph).hasEntrySatisfying("light red") {
            assertTrue(it.canContain(Bag("bright white")))
            assertTrue(it.canContain(Bag("muted yellow")))
        }
    }

    @Test
    fun test_find_bags_that_can_hold_my_shiny_gold_bag(){
        assertThat(Day7().solvePart1(testInput)).isEqualTo(4)
    }

    @Test
    fun test_shiny_gold_bag_must_contain_32_bags(){
        assertThat(Day7().solvePart2(testInput)).isEqualTo(32)
    }

}