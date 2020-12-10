package com.github.juwit.adventofcode2020

fun Pair<Int,Int>.hasADifferenceOf(jolt:Int): Boolean = this.second - this.first == jolt

class Day10: Day(10, "Adapter Array") {

    override fun solvePart1(input: List<String>): String {
        val adapterChain = input.map(String::toInt)
            .sorted()
            .toMutableList()

        adapterChain.add(0, 0) // charging outlet
        val buildInAdapter = adapterChain.last() + 3
        adapterChain.add(buildInAdapter)

        val oneDiff = adapterChain.zipWithNext().count { it.hasADifferenceOf(1) }
        val threeDiff = adapterChain.zipWithNext().count { it.hasADifferenceOf(3) }

        return (oneDiff * threeDiff).toString()
    }

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }
}