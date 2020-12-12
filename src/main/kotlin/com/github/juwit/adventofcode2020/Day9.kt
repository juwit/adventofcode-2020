package com.github.juwit.adventofcode2020

import java.lang.IllegalArgumentException

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

    override fun solvePart1(input: List<String>): Long {
        val inputInts = input.map(String::toLong)
        val wrongIndex = (preamble until input.size-1).find {
            ! isPositionRight(it, inputInts, preamble)
        }
        return input[wrongIndex!!].toLong()
    }

    override fun solvePart2(input: List<String>): Long {
        val wrongValue = solvePart1(input).toLong()
        val inputInts = input.map(String::toLong)

        (0..input.size).forEach { index ->
            val sequenceEndIdx = inputInts.sumToMaxIndex(index, wrongValue)
            if(sequenceEndIdx != -1){
                val contiguousSequence = inputInts.subList(index, sequenceEndIdx)
                return (contiguousSequence.minOrNull()!! + contiguousSequence.maxOrNull()!!)
            }
        }

        throw IllegalArgumentException("answer not found !")
    }
}

/**
 * Find the X firsts numbers of the list whose sum equals the requested value, then returns X, or -1
 * The calculation starts at `startIdx`
 */
fun List<Long>.sumToMaxIndex(startIdx: Int, maxValue: Long): Int {
    var acc = 0L
    for( i in (startIdx until this.size-1) ) {
        acc += this[i]

        if (acc == maxValue) {
            return i
        }
        if (acc > maxValue) {
            return -1
        }
    }
    return -1
}