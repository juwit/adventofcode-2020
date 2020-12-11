package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day11Test {

    private val testInput = """
        L.LL.LL.LL
        LLLLLLL.LL
        L.L.L..L..
        LLLL.LL.LL
        L.LL.LL.LL
        L.LLLLL.LL
        ..L.L.....
        LLLLLLLLLL
        L.LLLLLL.L
        L.LLLLL.LL
    """.trimIndent().asStringList()

    private val afterOneRoundExpected = """
        #.##.##.##
        #######.##
        #.#.#..#..
        ####.##.##
        #.##.##.##
        #.#####.##
        ..#.#.....
        ##########
        #.######.#
        #.#####.##
    """.trimIndent().asStringList()

    private val afterOneTwoExpected = """
        #.LL.L#.##
        #LLLLLL.L#
        L.L.L..L..
        #LLL.LL.L#
        #.LL.LL.LL
        #.LLLL#.##
        ..L.L.....
        #LLLLLLLL#
        #.LLLLLL.L
        #.#LLLL.##
    """.trimIndent().asStringList()

    @Test
    fun `If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied`(){
        assertThat(nextState(false, listOf(false, false))).isTrue
        assertThat(nextState(false, listOf(false, false, false, false))).isTrue
    }

    @Test
    fun `If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty`(){
        assertThat(nextState(true, listOf(true, true, true, true))).isFalse
        assertThat(nextState(true, listOf(true, true, true, true, true))).isFalse
        assertThat(nextState(true, listOf(true, true, true, true, false))).isFalse
    }

    @Test
    fun `Otherwise, the seat's state does not change`(){
        assertThat(nextState(true, listOf(true, true, true))).isTrue
        assertThat(nextState(true, listOf(true, true, true, false, false))).isTrue

        assertThat(nextState(false, listOf(true, true, true))).isFalse
        assertThat(nextState(false, listOf(true, false, true))).isFalse
    }

    @Test
    fun test_parse_waiting_area_as_booleans(){
        assertThat(parseInput(testInput)[0])
            .isEqualTo(listOf(false, null, false, false, null, false, false, null, false, false))

        assertThat(parseInput(afterOneRoundExpected)[0])
            .isEqualTo(listOf(true, null, true, true, null, true, true, null, true, true))
    }

    @Test
    fun test_round_one(){
        val waitingArea = parseInput(testInput)

        val afterRoundOne = oneRound(waitingArea, ::adjacentSeats, 4).toWaitingAreaString()

        assertThat(afterRoundOne).isEqualTo(afterOneRoundExpected)
    }

    @Test
    fun test_round_two(){
        val waitingArea = parseInput(afterOneRoundExpected)

        val afterRoundOne = oneRound(waitingArea, ::adjacentSeats, 4).toWaitingAreaString()

        assertThat(afterRoundOne).isEqualTo(afterOneTwoExpected)
    }

    @Test
    fun test_solve_part_one(){
        assertThat(Day11().solvePart1(testInput)).isEqualTo("37")
    }

    @Test
    fun `For example, the empty seat below would see eight occupied seats`() {
        val input = """
            .......#.
            ...#.....
            .#.......
            .........
            ..#L....#
            ....#....
            .........
            #........
            ...#.....
        """.trimIndent().asStringList()

        val waitingArea = parseInput(input)

        assertThat(visibleSeats(waitingArea,4,3))
            .hasSize(8)
            .allMatch { it == true}
    }

    @Test
    fun `The leftmost empty seat below would only see one empty seat, but cannot see any of the occupied ones`(){
        val input = """
            .............
            .L.L.#.#.#.#.
            .............
        """.trimIndent().asStringList()

        val waitingArea = parseInput(input)

        assertThat(visibleSeats(waitingArea,1,1)).contains(false)
    }

    @Test
    fun test_solve_part_two(){
        assertThat(Day11().solvePart2(testInput)).isEqualTo("26")
    }

}