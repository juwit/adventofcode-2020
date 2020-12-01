package com.github.juwit.adventofcode2020.day01

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

fun String.asString(): String = {}.javaClass.getResource(this).readText()

fun String.asStringList(): List<String> = this.asString().split("\n")

fun String.asIntList(): List<Int> = this.asStringList().map { it.toInt() }