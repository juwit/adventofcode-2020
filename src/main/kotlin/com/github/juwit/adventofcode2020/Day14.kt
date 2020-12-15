package com.github.juwit.adventofcode2020

fun Long.toBinaryString(): String = this.toString(2).padStart(36,'0')

fun Long.applyMask(mask: String): String {
    // convert long to binary string
    val binaryString = this.toBinaryString()
    val maskedString = binaryString.mapIndexed { index, c ->
        when(mask[index]){
            '0' -> '0'
            '1' -> '1'
            else -> c
        }
    }
    return String(maskedString.toCharArray())
}

fun Long.applyMaskVersion2(mask: String): List<String> {
    // convert long to binary string
    val binaryString = this.toBinaryString()
    val maskedString = binaryString.mapIndexed { index, c ->
        when(mask[index]){
            '0' -> c
            '1' -> '1'
            else -> 'X' // fork here !
        }
    }

    // expanding memory address results
    val resultList = mutableListOf<String>(String(maskedString.toCharArray()))
    while(resultList.first().contains('X')){
        val maskedStringToFork = resultList.removeAt(0)
        resultList.add(maskedStringToFork.replaceFirst('X', '0'))
        resultList.add(maskedStringToFork.replaceFirst('X', '1'))
    }

    return resultList
}

class BitmaskSystemProgram(val input: List<String>){

    // use a map String/String as memory, as arrays are indexed with ints (2^24)
    val memory = mutableMapOf<String,String>()
    var currentMask = ""

    private val maskRegex = Regex("mask = (.*)")
    private val memoryWriteRegex = Regex("mem\\[(\\d+)] = (\\d+)")

    fun runVersion1(): Long {
        input.forEach {
            when {
                it.matches(maskRegex) -> currentMask = maskRegex.matchEntire(it)!!.destructured.component1()
                it.matches(memoryWriteRegex) -> {
                    val (address, value) = memoryWriteRegex.matchEntire(it)!!.destructured
                    memory[address.toLong().toBinaryString()] = value.toLong().applyMask(currentMask)
                }
            }
        }
        return memory.values.sumOf { it.toLong(2) }
    }

    fun runVersion2(): Long {
        input.forEach {
            when {
                it.matches(maskRegex) -> currentMask = maskRegex.matchEntire(it)!!.destructured.component1()
                it.matches(memoryWriteRegex) -> {
                    val (address, value) = memoryWriteRegex.matchEntire(it)!!.destructured
                    // expand address with mask
                    val memoryAddresses = address.toLong().applyMaskVersion2(currentMask)
                    // write values
                    memoryAddresses.forEach {
                        memory[it] = value
                    }
                }
            }
        }
        return memory.values.sumOf { it.toLong() }
    }
}

class Day14 : Day(14, "Docking Data") {

    override fun solvePart1(input: List<String>): Number {
        return BitmaskSystemProgram(input).runVersion1()
    }

    override fun solvePart2(input: List<String>): Number {
        return BitmaskSystemProgram(input).runVersion2()
    }
}