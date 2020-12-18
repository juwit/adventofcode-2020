package com.github.juwit.adventofcode2020

import picocli.CommandLine
import java.lang.IllegalArgumentException
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@CommandLine.Command(name = "adventofcode-2020")
class AdventOfCode : Callable<Int> {

    @CommandLine.Option(names = ["-d", "--day"], description = ["the day to run"])
    private var dayNumber = 0

    @CommandLine.Option(names = ["--all"], description = ["run all days"])
    private var runAllDays = false


    override fun call(): Int {
        println("[AdventOfCode - 2020]")
        println()

        // autoloading all days
        // avoiding using reflection as it will not work with native-image
        val days =
            listOf(
                Day1(),
                Day2(),
                Day3(),
                Day4(),
                Day5(),
                Day6(),
                Day7(),
                Day8(),
                Day9(),
                Day10(),
                Day11(),
                Day12(),
                Day13(),
                Day14(),
                Day15(),
                Day16(),
                Day17()
            )

        if (runAllDays) {
            days.forEach {
                it.solveDay()
            }
        } else if (dayNumber != 0) {
            val day = days.find { it.id == dayNumber } ?: throw IllegalArgumentException("could not find day #$dayNumber")
            day.solveDay()
            return 0
        }
        days.last().solveDay()
        return 0
    }

}

fun main(args: Array<String>) {
    exitProcess(CommandLine(AdventOfCode()).execute(*args))
}