package day04

import println
import readInput
import java.util.stream.IntStream

private const val DAY = "day04";
fun main() {

    fun part1(file: List<String>): Int {
        var sum = 0;
        IntStream.range(0, file.size).forEach{ y ->
            val line = file[y].split(Regex("(:|\\|)"))
            println(line)
            val winning = line[1].trim().split(" ").mapNotNull(String::toIntOrNull).toSet();
            val victory = line[2].trim().split(" ").mapNotNull(String::toIntOrNull).filter { winning.contains(it) }.count();
            if(victory > 0){
                var v = 1;
                IntStream.range(1,victory).forEach { v *= 2 }
                println(v)
                sum += v
            }
        }
        println(sum)
        return sum;
    }



    fun part2(file: List<String>): Int {
        var sum = 0;
        var wonTickets : MutableList<Int> = mutableListOf();
        IntStream.range(0, file.size).forEach{ y ->
            val line = file[y].split(Regex("(:|\\|)"))
            println(line)
            println(wonTickets)
            val winning = line[1].trim().split(" ").mapNotNull(String::toIntOrNull).toSet();
            val victory = line[2].trim().split(" ").mapNotNull(String::toIntOrNull).filter { winning.contains(it) }.count();
            sum += 1 + wonTickets.size;
            if(victory > 0){
                println(wonTickets.size)
                println(sum)
                if(wonTickets.isNotEmpty()){
                    val wonTicketSize = wonTickets.size;
                    wonTickets = wonTickets.map { it-1 }.filter { it>0 }.toMutableList()
                    IntStream.range(0,wonTicketSize).forEach { wonTickets.add(victory) }
                }
                wonTickets.add(victory)
            } else{
                if(wonTickets.isNotEmpty()){
                    wonTickets = wonTickets.map { it-1 }.filter { it>0 }.toMutableList()
                }
            }
        }
        println(sum)
        return sum;
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(DAY, true)
    check(part1(testInput) == 13)
    val input = readInput(DAY, false)
    part1(input).println()
    println("---NEXT PART---")
    check(part2(testInput) == 30)
    part2(input).println()
}
