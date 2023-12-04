package day01

import println
import readInput
import kotlin.streams.toList

private const val DAY = "day01";
fun main() {


    fun part1(input: List<String>): Int {
        var sum = 0;
        for (line in input){
            val digits = line.toList().stream().filter(Char::isDigit).map(Char::digitToInt).toList();
            val firstNum = digits.first();
            val lastNum = digits.last();
            sum += (firstNum*10)+lastNum;
        }
        println(sum)
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0;
        for (line in input){
            val newline = line.replace("one","o1e")
                    .replace("two","t2o")
                    .replace("three","t3e")
                    .replace("four","f4r")
                    .replace("five","f5e")
                    .replace("six","s6x")
                    .replace("seven","s7n")
                    .replace("eight","e8t")
                    .replace("nine","n9e")
            println(newline)
            val digits = newline.toList().stream().filter(Char::isDigit).map(Char::digitToInt).toList();
            val firstNum = digits.first();
            val lastNum = digits.last();
            println("${firstNum}${lastNum}")
            sum += (firstNum*10)+lastNum;
        }
        println(sum)
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(DAY, true)
    //check(part1(testInput) == 142)
    check(part2(testInput) == 281)

    val input = readInput(DAY, false)
    part1(input).println()
    part2(input).println()
}
