package com.github.juwit.adventofcode2020

class GroupAnswers() {

    val answersList = mutableListOf<String>()

    fun addAnswer(answer: String) = answersList.add(answer)

    fun countYesAnswers(): Int {
        return answersList
                // joining all strings
            .reduce { acc, s -> acc+s }
                // splitting to make a char list
            .toCharArray()
                // count distinct chars
            .distinct()
            .count()
    }
}

fun parseGroupAnswers(allAnswers: List<String>): List<GroupAnswers> {
    val groupAnswers = mutableListOf<GroupAnswers>()
    var groupAnswer = GroupAnswers()
    groupAnswers.add(groupAnswer)
    allAnswers.forEach {
        if(it.isNotBlank()){
            groupAnswer.addAnswer(it)
        }
        else{
            groupAnswer = GroupAnswers()
            groupAnswers.add(groupAnswer)
        }
    }
    return groupAnswers
}

class Day6: Day(6, "Custom Customs") {

    override fun solvePart1(input: List<String>): String {
        return parseGroupAnswers(input)
            .map { it.countYesAnswers() }
            .sum()
            .toString()
    }

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }
}