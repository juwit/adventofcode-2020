package com.github.juwit.adventofcode2020

sealed class Instruction(){
    var visited = false
    abstract fun execute(p: Program)
}

data class JumpInstruction(val toRelativePosition: Int): Instruction() {
    override fun execute(p: Program) {
        this.visited = true
        p.codePointer += toRelativePosition
    }
    companion object {
        val regex = Regex("jmp ([+-]\\d+)")
        fun parse(line: String): JumpInstruction {
            val (value) = regex.find(line)!!.destructured
            return JumpInstruction(value.toInt())
        }
    }
}

data class NoOpInstruction(val dummyValue: Int): Instruction() {
    override fun execute(p: Program) {
        this.visited = true
        p.codePointer++
    } // nothing to do
    companion object {
        val regex = Regex("nop ([+-]\\d+)")
        fun parse(line: String): NoOpInstruction {
            val (value) = regex.find(line)!!.destructured
            return NoOpInstruction(value.toInt())
        }
    }
}

data class AccInstruction(val increment: Int): Instruction() {
    override fun execute(p: Program) {
        this.visited = true
        p.accumulator += increment
        p.codePointer++
    }
    companion object {
        val regex = Regex("acc ([+-]\\d+)")
        fun parse(line: String): AccInstruction {
            val (value) = regex.find(line)!!.destructured
            return AccInstruction(value.toInt())
        }
    }
}

fun parseInstruction(line: String): Instruction {
    return when {
        NoOpInstruction.regex.matches(line) -> NoOpInstruction.parse(line)
        AccInstruction.regex.matches(line) -> AccInstruction.parse(line)
        JumpInstruction.regex.matches(line) -> JumpInstruction.parse(line)
        else -> throw IllegalArgumentException("Not an instruction")
    }

}

fun parseProgramInstructions(input:List<String>): List<Instruction>{
    return input.map { parseInstruction(it) }
}

class Program(val instructions:List<Instruction>){
    var codePointer = 0
    var accumulator = 0

    fun runUntilLoop(): Int {
        while(!instructions[codePointer].visited){
            instructions[codePointer].execute(this)
        }
        return accumulator
    }

}

class Day8: Day(8, "Handheld Halting") {

    override fun solvePart1(input: List<String>): String {
        val instructions = parseProgramInstructions(input)
        return Program(instructions).runUntilLoop().toString()
    }

    override fun solvePart2(input: List<String>): String {
        TODO("Not yet implemented")
    }
}