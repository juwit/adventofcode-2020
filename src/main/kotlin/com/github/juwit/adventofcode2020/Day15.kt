package com.github.juwit.adventofcode2020

class Day15 : Day(15, "Rambunctious Recitation") {

    fun nextNumber(alreadySpoken: List<Long>): Long {
        val lastSpoken = alreadySpoken.last()

        if (alreadySpoken.count { it == lastSpoken } == 1) {
            return 0
        } else {
            // getting last index of number
            val lastSpokenIndex = alreadySpoken.size.toLong()
            val previouslyLastSpokenIndex = alreadySpoken.subList(0, alreadySpoken.size - 1).lastIndexOf(lastSpoken) + 1

            return lastSpokenIndex - previouslyLastSpokenIndex
        }

        return 0
    }

    override fun solvePart1(input: List<String>): Number {
        val numbers = input[0].split(",").map { it.toLong() }.toMutableList()

        for (i in numbers.size until 2020) {
            numbers.add(nextNumber(numbers))
        }

        return numbers.last()
    }

    override fun solvePart2(input: List<String>): Number {
        TODO("Not yet implemented")
    }
}