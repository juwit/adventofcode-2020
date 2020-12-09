package com.github.juwit.adventofcode2020

fun isPositionRight(position: Int, input: List<Long>, preamble:Int = 25): Boolean {
    val valueAtPosition = input[position]

    val subList = input.subList(position - preamble, position)

    subList.forEach { a ->
        subList.forEach { b ->
            if(a != b && a + b == valueAtPosition) return true
        }
    }
    return false
}

class Day9(val preamble: Int = 25): Day(9, "Encoding Error") {

    override fun solvePart1(input: List<String>): String {
        val inputInts = input.map(String::toLong)
        val wrongIndex = (preamble until input.size-1).find {
            ! isPositionRight(it, inputInts, preamble)
        }
        return input[wrongIndex!!]
    }

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }
}
