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
        val numbers = input[0].split(",").map { it.toLong() }.toMutableList()

        // first implementation was using naive technique, iterating in the list
//        for (i in numbers.size until 30_000_000) {
//            numbers.add(nextNumber(numbers))
//        }

        // try with an intelligent map, which contains only the last index for each number
        var currentNumberToSpeak = nextNumber(numbers)

        val lastSpokenIndexMap = numbers.mapIndexed { index, number -> number to index + 1 }.toMap().toMutableMap()

        for (turn in numbers.size + 1 until 30_000_000) {
            val lastIndex = lastSpokenIndexMap.getOrDefault(currentNumberToSpeak, 0)
            lastSpokenIndexMap[currentNumberToSpeak] = turn
            currentNumberToSpeak = if (lastIndex == 0) {
                0L
            } else {
                (turn - lastIndex).toLong()
            }
        }


        return currentNumberToSpeak
    }
}