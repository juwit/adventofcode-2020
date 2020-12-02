package com.github.juwit.adventofcode2020

interface PasswordPolicy {
    fun isValid(password: String): Boolean
}

data class CountPasswordPolicy(val min: Int, val max: Int, val letter: Char) : PasswordPolicy {
    override fun isValid(password: String): Boolean {
        val letterCount = password.count { it == letter }
        return (letterCount >= min) && (letterCount <= max)
    }
}

data class PositionsPasswordPolicy(val posA: Int, val posB: Int, val letter: Char) : PasswordPolicy {
    override fun isValid(password: String): Boolean {
        return (password[posA - 1] == letter) xor (password[posB - 1] == letter)
    }
}

fun String.toCountPasswordPolicyAndPassword(): Pair<PasswordPolicy, String> {
    val (min, max, letter, password) = Regex("^(\\d+)-(\\d+) (\\w): (\\w+)$").find(this)!!.destructured
    return Pair(CountPasswordPolicy(min.toInt(), max.toInt(), letter[0]), password)
}

fun String.toPositionPasswordPolicyAndPassword(): Pair<PasswordPolicy, String> {
    val (posA, posB, letter, password) = Regex("^(\\d+)-(\\d+) (\\w): (\\w+)$").find(this)!!.destructured
    return Pair(PositionsPasswordPolicy(posA.toInt(), posB.toInt(), letter[0]), password)
}


class Day2 : Day(2, "Password Philosophy") {

    override fun solvePart1(input: List<String>): String {
        val inputAsPasswordPolicy = input.map { it.toCountPasswordPolicyAndPassword() }
        return inputAsPasswordPolicy.count { (policy, password) -> policy.isValid(password) }.toString()
    }

    override fun solvePart2(input: List<String>): String {
        val inputAsPasswordPolicy = input.map { it.toPositionPasswordPolicyAndPassword() }
        return inputAsPasswordPolicy.count { (policy, password) -> policy.isValid(password) }.toString()
    }


}