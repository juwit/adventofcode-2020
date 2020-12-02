package com.github.juwit.adventofcode2020

data class PasswordPolicy(val min:Int, val max:Int, val letter:Char){
    fun isValid(password: String):Boolean{
        val letterCount = password.count { it == letter }
        return (letterCount >= min) && (letterCount <= max)
    }
}

fun String.toPasswordPolicyAndPassword():Pair<PasswordPolicy, String> {
    val (min, max, letter, password) = Regex("^(\\d+)-(\\d+) (\\w): (\\w+)$").find(this)!!.destructured
    return Pair(PasswordPolicy(min.toInt(), max.toInt(), letter.get(0)), password)
}

class Day2: Day(2, "Password Philosophy"){

    override fun solvePart1(input: List<String>): String {
        val inputAsPasswordPolicy = input.map { it.toPasswordPolicyAndPassword() }
        return inputAsPasswordPolicy.count { it.first.isValid(it.second) }.toString()

    }

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }


}