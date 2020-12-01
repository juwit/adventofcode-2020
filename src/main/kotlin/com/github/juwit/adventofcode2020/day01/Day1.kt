package com.github.juwit.adventofcode2020.day01

val input = "/input-day-01.txt".asIntList()

fun solve_part1() {
    println("--- Day 1: Report Repair ---")

    val input = "/input-day-01.txt".asIntList()

    for (a in input) {
        for(b in input) {
            if(a+b == 2020){
                println("Puzzle answer : ${a*b}");
                return
            }
        }
    }
}

fun solve_part2() {
    println("--- Part Two ---");
    for (a in input) {
        for(b in input) {
            for(c in input) {
                if(a+b+c == 2020){
                    println("Puzzle answer : ${a*b*c}");
                    return
                }
            }
        }
    }
}

fun String.asString(): String = {}.javaClass.getResource(this).readText()

fun String.asStringList(): List<String> = this.asString().split("\n")

fun String.asIntList(): List<Int> = this.asStringList().map { it.toInt() }