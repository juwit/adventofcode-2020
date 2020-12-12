package com.github.juwit.adventofcode2020

sealed class Instruction() {
    var visited = false
    abstract fun execute(p: Program)
    abstract fun mutate(): Instruction
}

data class JumpInstruction(val toRelativePosition: Int) : Instruction() {
    override fun execute(p: Program) {
        this.visited = true
        p.codePointer += toRelativePosition
    }

    override fun mutate(): Instruction {
        return NoOpInstruction(toRelativePosition)
    }

    companion object {
        val regex = Regex("jmp ([+-]\\d+)")
        fun parse(line: String): JumpInstruction {
            val (value) = regex.find(line)!!.destructured
            return JumpInstruction(value.toInt())
        }
    }
}

data class NoOpInstruction(val dummyValue: Int) : Instruction() {
    override fun execute(p: Program) {
        this.visited = true
        p.codePointer++
    } // nothing to do

    override fun mutate(): Instruction {
        return JumpInstruction(dummyValue)
    }

    companion object {
        val regex = Regex("nop ([+-]\\d+)")
        fun parse(line: String): NoOpInstruction {
            val (value) = regex.find(line)!!.destructured
            return NoOpInstruction(value.toInt())
        }
    }
}

data class AccInstruction(val increment: Int) : Instruction() {
    override fun execute(p: Program) {
        this.visited = true
        p.accumulator += increment
        p.codePointer++
    }

    override fun mutate() = this

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

fun parseProgramInstructions(input: List<String>): List<Instruction> {
    return input.map { parseInstruction(it) }
}

class Program(val instructions: List<Instruction>) {
    var codePointer = 0
    var accumulator = 0

    fun runUntilLoop(): Int {
        while (!instructions[codePointer].visited) {
            instructions[codePointer].execute(this)
        }
        return accumulator
    }

    fun runToTheEnd(): Int {
        while (codePointer < instructions.size) {
            instructions[codePointer].execute(this)

            if(codePointer >= instructions.size) {
                return accumulator
            }
            if (instructions[codePointer].visited) {
                throw java.lang.IllegalArgumentException("loop detected")
            }
        }
        return accumulator
    }

}

class Day8 : Day(8, "Handheld Halting") {

    override fun solvePart1(input: List<String>): Long {
        val instructions = parseProgramInstructions(input)
        return Program(instructions).runUntilLoop().toLong()
    }

    override fun solvePart2(input: List<String>): Long {
        val instructions = parseProgramInstructions(input).toMutableList()

        // ok so now, we have to change the program !

        // find jump/noop indexes
        val operationsToMutateIndex = instructions.flatMapIndexed { index, instruction ->
            when (instruction) {
                is NoOpInstruction -> listOf(index)
                is JumpInstruction -> listOf(index)
                else -> listOf()
            }
        }

        operationsToMutateIndex.forEach {
            val newInstructions = instructions.toMutableList()
            // reset program
            newInstructions.forEach { instruction ->  instruction.visited = false }
            // mutate operation
            newInstructions[it] = newInstructions[it].mutate()
            try {
                // try to execute to the end with the mutation
                return Program(newInstructions).runToTheEnd().toLong()
            }
            catch (e:java.lang.IllegalArgumentException){
                // not this one ! next !
            }
        }
        throw Exception("could not find puzzle answer")
    }
}