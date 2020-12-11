package com.github.juwit.adventofcode2020

import java.lang.Integer.min

fun Pair<Int,Int>.hasADifferenceOf(jolt:Int): Boolean = this.second - this.first == jolt

data class Adapter(val joltage: Int){

    private val compatibleAdapters = mutableListOf<Adapter>()

    private var memo: Long? = null

    fun isCompatibleWith(otherAdapter: Adapter): Boolean = otherAdapter.joltage - this.joltage <= 3

    fun plug(otherAdapter: Adapter) {
        this.compatibleAdapters.add(otherAdapter)
    }

    // funny : I first tried with Int, which gave a result, but a wrong one (type overflow)
    fun countPath(): Long{
        if(memo != null){
            return memo!!
        }
        if(compatibleAdapters.isEmpty()){
            return 1 // last adapter
        }

        memo = compatibleAdapters.sumOf { it.countPath() }
        return memo!!
    }

}

class Day10: Day(10, "Adapter Array") {

    private fun buildAdapterChain(input: List<String>): MutableList<Int> {
        val adapterChain = input.map(String::toInt)
            .sorted()
            .toMutableList()

        adapterChain.add(0, 0) // charging outlet
        val buildInAdapter = adapterChain.last() + 3
        adapterChain.add(buildInAdapter)

        return adapterChain
    }

    override fun solvePart1(input: List<String>): String {
        val adapterChain = buildAdapterChain(input)

        val oneDiff = adapterChain.zipWithNext().count { it.hasADifferenceOf(1) }
        val threeDiff = adapterChain.zipWithNext().count { it.hasADifferenceOf(3) }

        return (oneDiff * threeDiff).toString()
    }

    override fun solvePart2(input: List<String>): String {
        val adapterChain = buildAdapterChain(input)

        // build a graph from the ordered chain !
        val adapterGraph = adapterChain.map(::Adapter)

        adapterGraph.forEachIndexed { idx, adapter ->
            if(idx < adapterGraph.size - 1){
                // looking for compatible adapters 3 adapters away
                adapterGraph.subList(idx+1, min(adapterGraph.size, idx +4)).forEach { otherAdapter ->
                    if(adapter.isCompatibleWith(otherAdapter)){
                        adapter.plug(otherAdapter)
                    }
                }
            }
        }

        // count all the paths
        return adapterGraph[0].countPath().toString()
    }
}