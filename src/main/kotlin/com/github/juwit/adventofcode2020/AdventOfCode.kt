package com.github.juwit.adventofcode2020

import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(name="adventofcode-2020")
class AdventOfCode: Callable<Int> {

    @CommandLine.Option(names=["-d", "--day"], description = ["the day to run"])
    private var dayNumber = 0

    @CommandLine.Option(names=["--all"], description = ["run all days"])
    private var runAllDays = false


    override fun call(): Int {
        println("[AdventOfCode - 2020]")
        println()

        // autoloading all days
        (1..15).forEach {
            Class.forName("com.github.juwit.adventofcode2020.Day$it").getConstructor().newInstance()
        }

        if(runAllDays){
            days.forEach {
                it.solveDay()
            }
        }
        else if(dayNumber != 0){
            val day = days.find { it.id == dayNumber }
            if(day == null){
                println("could not find day #$dayNumber")
            }
            day?.solveDay()
            return 0
        }
        days.last().solveDay()
        return 0
    }

}

fun main(args: Array<String>) {
    System.exit(CommandLine(AdventOfCode()).execute(*args))
}