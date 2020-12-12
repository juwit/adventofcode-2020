package com.github.juwit.adventofcode2020

data class Bag(val color: String){

    private val canContain = mutableMapOf<Bag, Int>()

    fun linkTo(count: Int, bag: Bag) {
        // store link
        this.canContain[bag] = count
    }

    fun canContain(bag:Bag): Boolean {
        return this.canContain.containsKey(bag) // direct link
                // traversal link
                || canContain.keys.any { it.canContain(bag) }
    }

    fun countContainedBags(): Int {
        return canContain.values.sum() + canContain.entries.sumBy { it.key.countContainedBags() * it.value }
    }
}

fun parseBagsGraph(input: List<String>): Map<String, Bag> {
    val bagNameRegex = Regex("(\\w+ \\w+) bags contain")
    val bagSpecRegex = Regex("(\\d+) (\\w+ \\w+) bags?")

    val bagsRegistry = mutableMapOf<String, Bag>()

    input.forEach {
        val (bagName) = bagNameRegex.find(it)!!.destructured
        // get existing bag, or create it
        val bag = bagsRegistry.getOrPut(bagName, { Bag(bagName) })

        bagSpecRegex.findAll(it).forEach {
            val (count, containedBagName) = it.destructured
            val linkedBag = bagsRegistry.getOrPut(containedBagName, { Bag(containedBagName) })
            bag.linkTo(count.toInt(), linkedBag)
        }
    }

    return bagsRegistry
}

class Day7: Day(7, "Handy Haversacks") {
    override fun solvePart1(input: List<String>): Long {
        // parse graph
        val bagsGraph = parseBagsGraph(input)
        // get "shiny gold" bag
        val shinyGold = bagsGraph["shiny gold"]!!

        return bagsGraph.values.count { it.canContain(shinyGold) }.toLong()
    }

    override fun solvePart2(input: List<String>): Long {
        // parse graph
        val bagsGraph = parseBagsGraph(input)
        // get "shiny gold" bag
        val shinyGold = bagsGraph["shiny gold"]!!

        return shinyGold.countContainedBags().toLong()
    }
}