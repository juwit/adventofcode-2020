package com.github.juwit.adventofcode2020

fun Long.toBinaryString(): String = this.toString(2).padStart(36,'0')

fun Long.applyMask(mask: String): Long {
    // convert long to binary string
    val binaryString = this.toBinaryString()
    val maskedString = binaryString.mapIndexed { index, c ->
        when(mask[index]){
            '0' -> '0'
            '1' -> '1'
            else -> c
        }
    }
    return String(maskedString.toCharArray()).toLong(2)
}

class BitmaskSystemProgram(val input: List<String>){

    val memory:LongArray = LongArray(100_000) { 0 }
    var currentMask = ""

    private val maskRegex = Regex("mask = (.*)")
    private val memoryWriteRegex = Regex("mem\\[(\\d+)] = (\\d+)")

    fun run(): Long {
        input.forEach {
            when {
                it.matches(maskRegex) -> currentMask = maskRegex.matchEntire(it)!!.destructured.component1()
                it.matches(memoryWriteRegex) -> {
                    val (address, value) = memoryWriteRegex.matchEntire(it)!!.destructured
                    memory[address.toInt()] = value.toLong().applyMask(currentMask)
                }
            }
        }
        return memory.sum()
    }
}

class Day14 : Day(14, "Docking Data") {

    override fun solvePart1(input: List<String>): Number {
        return BitmaskSystemProgram(input).run()
    }

    override fun solvePart2(input: List<String>): Number {
        TODO("Not yet implemented")
    }
}