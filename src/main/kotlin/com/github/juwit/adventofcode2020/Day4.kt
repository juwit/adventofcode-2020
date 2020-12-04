package com.github.juwit.adventofcode2020

data class Passport(val map: Map<String, String>) {
    val byr by map
    val iyr by map
    val eyr by map
    val hgt by map
    val hcl by map
    val ecl by map
    val pid by map
    val cid by map

    // to be valid, all fields MUST have a value, except "cid"
    fun hasAllRequiredFields() = byr.isNotBlank()
            && iyr.isNotBlank()
            && eyr.isNotBlank()
            && hgt.isNotBlank()
            && hcl.isNotBlank()
            && ecl.isNotBlank()
            && pid.isNotBlank()
}

fun parsePassportLine(line: String): Map<String, String> {
    // parse the line using magic regex
    val keyValueRegex = Regex("(.*):(.*)")

    return line.split(" ") // splitting line by " "
        .map { keyValueRegex.find(it)!!.destructured } // matching the regex to capture key & value
        .map { (key, value) -> key to value } // convert destructured matches to Pair
        .toMap() // return a Map of the Pairs
}

fun parsePassports(input: List<String>): List<Passport> {
    val list = mutableListOf<Passport>()

    var fieldsMap = mutableMapOf<String, String>().withDefault { "" }

    input.forEach {
        if (it.isBlank()) {
            list.add(Passport(fieldsMap))
            // use a new map for now
            fieldsMap = mutableMapOf<String, String>().withDefault { "" }
        } else {
            fieldsMap.putAll(parsePassportLine(it))
        }
    }

    // manage last line
    list.add(Passport(fieldsMap))
    return list
}

class Day4 : Day(4, "Passport Processing") {

    override fun solvePart1(input: List<String>): String {
        return parsePassports(input).count { it.hasAllRequiredFields() }.toString()
    }

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }
}