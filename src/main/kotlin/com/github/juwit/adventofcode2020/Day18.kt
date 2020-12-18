package com.github.juwit.adventofcode2020

class Day18 : Day(18, "Operation Order") {

    fun evaluate(expression: String): Number {
        return Parser(Tokenizer(expression)).parseExpression().evaluate()
    }

    override fun solvePart1(input: List<String>): Number {
        return input.map { evaluate(it) }.sumOf { it.toLong() }
    }

    override fun solvePart2(input: List<String>): Number {
        TODO("Not yet implemented")
    }

}

// this is the syntax tre !
enum class Operator : Token {
    PLUS, MULT
}

enum class Parenthesis : Token {
    LEFT, RIGHT
}

interface Expression {
    fun evaluate(): Long
}

interface Token

data class Literal(val value: Long) : Expression, Token {
    override fun evaluate(): Long = value
}

data class OperationExpression(val left: Expression, val operator: Operator, val right: Expression) : Expression {
    override fun evaluate(): Long {
        return when (operator) {
            Operator.PLUS -> left.evaluate() + right.evaluate()
            Operator.MULT -> left.evaluate() * right.evaluate()
        }
    }
}

data class ParenthesisExpression(val content: Expression) : Expression {
    override fun evaluate() = content.evaluate()
}


// try to implement a tokenizer
class Tokenizer(val expression: String) : Iterable<Token> {

    class TokenIterator(val charArray: CharArray) : Iterator<Token> {

        private var currentIndex = 0

        override fun hasNext() = currentIndex in charArray.indices

        override fun next(): Token {
            // skip spaces
            while (charArray[currentIndex] == ' ') {
                currentIndex++
            }
            val currentChar = charArray[currentIndex]
            currentIndex++
            return when (currentChar) {
                '+' -> Operator.PLUS
                '*' -> Operator.MULT
                '(' -> Parenthesis.LEFT
                ')' -> Parenthesis.RIGHT
                else -> Literal(currentChar.toString().toLong())
            }
        }

    }

    override fun iterator(): Iterator<Token> {
        return TokenIterator(expression.toCharArray())
    }
}

// try to implement a parser for the grammar
// parenthesisExpression ::= LEFT_PARENT expression RIGHT_PARENT
// operatorExpression ::= LITERAL OPERATOR expression
//                      || parenthesisExpression OPERATOR expression
// expression ::=
//             | LITERAL
//             | parenthesisExpression
//             | operatorExpression
class Parser(tokenizer: Tokenizer) {
    val tokenList = tokenizer.map { it }
    var nextTokenIndex = 0

    fun hasNext() = nextTokenIndex in tokenList.indices

    fun nextToken(): Token {
        return tokenList[nextTokenIndex]
    }

    fun theTokenAfter(): Token? {
        if (nextTokenIndex + 1 in tokenList.indices) {
            return tokenList[nextTokenIndex + 1]
        }
        return null
    }

    fun theTokenAfterTheTokenAfter(): Token? {
        if (nextTokenIndex + 2 in tokenList.indices) {
            return tokenList[nextTokenIndex + 2]
        }
        return null
    }

    fun consumeToken(): Token {
        return tokenList[nextTokenIndex++]
    }

    fun parseExpression(): Expression {
        var expression = parseOperationExpression() ?: parseParenthesisExpression() ?: parseLiteral()!!
        while (hasNext() && nextToken() is Operator) {
            val (operator, rightHand) = parseRightHandOperationExpression()
            expression = OperationExpression(expression,operator, rightHand)
        }
        return expression
    }

    fun parseLiteral(): Expression? {
        if (nextToken() is Literal) {
            return consumeToken() as Literal
        } else {
            return null // not a literal expression
        }
    }

    fun parseOperationExpression(): Expression? {
        if (nextToken() is Literal && theTokenAfter() is Operator && theTokenAfterTheTokenAfter() is Literal) {
            val leftHand = parseLiteral()!!
            val operator = consumeToken() as Operator
            val rightHand = parseLiteral()!!
            return OperationExpression(leftHand, operator, rightHand)
        } else {
            return null
        }
    }

    fun parseRightHandOperationExpression(): Pair<Operator, Expression> {
        val operator = consumeToken() as Operator
        val rightHand = parseLiteral() ?: parseParenthesisExpression() ?: parseOperationExpression()!!
        return operator to rightHand
    }

    fun parseParenthesisExpression(): Expression? {
        if (nextToken() == Parenthesis.LEFT) {
            consumeToken() // consume left parenthesis
        } else {
            return null // not a parenthesis expression
        }
        // parseExpression
        val expression = parseExpression()
        if (nextToken() == Parenthesis.RIGHT) {
            consumeToken() // consume right parenthesis
        } else {
            // should throw a parse exception ?
            return null // not a parenthesis expression
        }
        return ParenthesisExpression(expression)
    }

}