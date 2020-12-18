package com.github.juwit.adventofcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day18Test {

    @Test
    fun testEvaluateSamples() {
        assertThat(Day18().evaluate("1 + 2 * 3 + 4 * 5 + 6")).isEqualTo(71L)

        assertThat(Day18().evaluate("1 + (2 * 3) + (4 * (5 + 6))")).isEqualTo(51L)

        assertThat(Day18().evaluate("2 * 3 + (4 * 5)")).isEqualTo(26L)

        assertThat(Day18().evaluate("5 + (8 * 3 + 9 + 3 * 4 * 3)")).isEqualTo(437L)

        assertThat(Day18().evaluate("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")).isEqualTo(12240L)

        assertThat(Day18().evaluate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")).isEqualTo(13632L)
    }

    @Test
    fun testParser(){
        assertThat(Parser(Tokenizer(("1"))).parseExpression().evaluate()).isEqualTo(1)

        assertThat(Parser(Tokenizer(("1 + 1"))).parseExpression().evaluate()).isEqualTo(2)

        assertThat(Parser(Tokenizer(("1 + 1 + 1"))).parseExpression().evaluate()).isEqualTo(3)

        assertThat(Parser(Tokenizer(("(1 + 1) + 1"))).parseExpression().evaluate()).isEqualTo(3)

        assertThat(Parser(Tokenizer(("1 + 2 * 3"))).parseExpression().evaluate()).isEqualTo(9)

        assertThat(Parser(Tokenizer(("1 + 2 * 3 + 4"))).parseExpression().evaluate()).isEqualTo(13)

        assertThat(Parser(Tokenizer(("1 + 2 * 3 + 4 * 5"))).parseExpression().evaluate()).isEqualTo(65)

        assertThat(Parser(Tokenizer(("1 + 2 * 3 + 4 * 5 + 6"))).parseExpression().evaluate()).isEqualTo(71)
    }

    @Test
    fun `1 + 2 * 3 + 4 * 5 + 6 tokenized`() {
        val tokenizer = Tokenizer("1 + 2 * 3 + 4 * 5 + 6")

        assertThat(tokenizer.map { it }).isEqualTo(
            listOf(
                Literal(1),
                Operator.PLUS,
                Literal(2),
                Operator.MULT,
                Literal(3),
                Operator.PLUS,
                Literal(4),
                Operator.MULT,
                Literal(5),
                Operator.PLUS,
                Literal(6)
            )
        )
    }

    @Test
    fun `1 + (2 * 3) + (4 * (5 + 6)) tokenized`() {
        val tokenizer = Tokenizer("1 + (2 * 3) + (4 * (5 + 6))")

        assertThat(tokenizer.map { it }).isEqualTo(
            listOf(
                Literal(1),
                Operator.PLUS,
                Parenthesis.LEFT,
                Literal(2),
                Operator.MULT,
                Literal(3),
                Parenthesis.RIGHT,
                Operator.PLUS,
                Parenthesis.LEFT,
                Literal(4),
                Operator.MULT,
                Parenthesis.LEFT,
                Literal(5),
                Operator.PLUS,
                Literal(6),
                Parenthesis.RIGHT,
                Parenthesis.RIGHT
            )
        )
    }

}