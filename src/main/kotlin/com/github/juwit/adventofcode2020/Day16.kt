package com.github.juwit.adventofcode2020

class Day16 : Day(16, "Ticket Translation") {

    data class FieldRule(val fieldName: String, val validRanges: List<IntRange>) {
        var possibleIndexes = mutableListOf<Int>()
        fun isValid(value: Int) = validRanges.any { it.contains(value) }
    }

    fun parseFieldsRules(input: List<String>): List<FieldRule> {
        val ruleRegex = Regex("([\\w ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)")

        val fieldsRules = input.subList(0, input.indexOf(""))
        return fieldsRules.map {
            val (fieldName, firstRangeStart, firstRangeEnd, secondRangeStart, secondRangeEnd) = ruleRegex.matchEntire(it)!!.destructured
            FieldRule(
                fieldName,
                listOf(firstRangeStart.toInt()..firstRangeEnd.toInt(), secondRangeStart.toInt()..secondRangeEnd.toInt())
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

        val nearbyTickets = parseNearbyTickets(input)

        val invalidFields = nearbyTickets.flatMap { it }.filter { fieldValue ->
            fieldsRules.none { it.isValid(fieldValue) }
        }
        return invalidFields.sum()
    }

    override fun solvePart2(input: List<String>): Number {
        val myTicket = input[input.indexOf("your ticket:") + 1].split(",").map { it.toInt() }

        val fieldsRules = parseFieldsRules(input)
        val allValidRanges = fieldsRules.flatMap { it.validRanges }

        val nearbyTickets = parseNearbyTickets(input)
        // first, filter invalid tickets
        val validNearbyTickets = nearbyTickets.filter {
            it.all { fieldValue -> allValidRanges.any { it.contains(fieldValue) } }
        }

        val ticketSize = myTicket.size

        val allValidTickets = listOf(myTicket) + validNearbyTickets
        fieldsRules.forEach { fieldRule ->
            // try to find rule location
            for (i in 0 until ticketSize) {
                if( allValidTickets.map { it[i] }.all { fieldRule.isValid(it) } ){
                    // found a match for the rule
                    fieldRule.possibleIndexes.add(i)
                }
            }
        }

        // now that we have possible indexes for each rule, we try to curate them
        while(fieldsRules.any { it.possibleIndexes.size > 1 }){
            val rulesWithUniqueIndexes = fieldsRules.filter { it.possibleIndexes.size == 1 }
            rulesWithUniqueIndexes.forEach { rule ->
                fieldsRules.filter { it != rule }.forEach { it.possibleIndexes.removeAll(rule.possibleIndexes) }
            }
        }

        return fieldsRules.filter { it.fieldName.startsWith("departure") }
            .map { myTicket[it.possibleIndexes[0]].toLong() }
            .reduce { acc, i -> acc * i }
    }

}