package com.github.juwit.adventofcode2020

class Day16 : Day(16, "Ticket Translation") {

    data class FieldRule(val fieldName: String, val validRange: IntRange)

    fun parseFieldsRules(input: List<String>): List<FieldRule> {
        val ruleRegex = Regex("([\\w ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)")

        val fieldsRules = input.subList(0, input.indexOf(""))
        return fieldsRules.flatMap {
            val (fieldName, firstRangeStart, firstRangeEnd, secondRangeStart, secondRangeEnd) = ruleRegex.matchEntire(it)!!.destructured
            listOf(
                FieldRule(fieldName, firstRangeStart.toInt()..firstRangeEnd.toInt()),
                FieldRule(fieldName, secondRangeStart.toInt()..secondRangeEnd.toInt())
            )
        }
    }

    fun parseNearbyTickets(input: List<String>): List<List<Int>> {
        val tickets = input.subList(input.indexOf("nearby tickets:") + 1, input.size)
        return tickets.map {
            it.split(",").map { it.toInt() }
        }
    }

    override fun solvePart1(input: List<String>): Number {
        val fieldsRules = parseFieldsRules(input)
        val allValidRanges = fieldsRules.map { it.validRange }

        val nearbyTickets = parseNearbyTickets(input)

        val invalidFields = nearbyTickets.flatMap { it }.filter { fieldValue ->
            allValidRanges.none { it.contains(fieldValue) }
        }
        return invalidFields.sum()
    }

    override fun solvePart2(input: List<String>): Number {
        TODO("Not yet implemented")
    }


}