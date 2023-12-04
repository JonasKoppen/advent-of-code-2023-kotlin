package day02

import println
import readInput
import kotlin.streams.toList

private const val DAY = "day02";
fun main() {

    val colours = mapOf("red" to 12, "green" to 13, "blue" to 14)

    fun part1(games: List<String>): Int {
        var invalidSum = 0;
        for (game in games){
            val g = game.split(':')
            var invalid = false;
            println(g)
            g[1].split(Regex("[,;]")).stream().map(String::trim).forEach {
                val s = it.split(' ')
                val maxCount = colours[(s[1])]
                val count = s[0].toInt()
                if (maxCount != null && maxCount < count){
                    //println(s)

                    //println(invalidSum)
                    invalid = true;
                }
            }
            if(!invalid){
                invalidSum += g[0].split(' ').last().toInt()
            }
        }
        println(invalidSum)
        return invalidSum
    }

    fun part2(games: List<String>): Int {
        var invalidSum = 0;
        for (game in games){
            val g = game.split(':')
            val minColours = mutableMapOf("red" to -1, "green" to -1, "blue" to -1)
            println(g)
            g[1].split(Regex("[,;]")).stream().map(String::trim).forEach {
                val s = it.split(' ')
                val minCount = minColours[(s[1])]
                val count = s[0].toInt();
                println(s)
                if(minCount == null || (minCount == -1)){
                    minColours[s[1]] = count
                }
                if(minCount != null && minCount < count){
                    minColours[s[1]] = count
                }
            }
            println(minColours.values)
            val minum = minColours.values.stream().reduce{ acc, next -> acc * next};
            println(minum)
            minum.ifPresent { m -> invalidSum += m}
        }
        println(invalidSum)
        return invalidSum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(DAY, true)
    check(part1(testInput) == 8)
    println("---NEXT PART---")
    check(part2(testInput) == 2286)

    val input = readInput(DAY, false)
    part1(input).println()
    part2(input).println()
}
