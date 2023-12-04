package day03

import println
import readInput
import java.util.stream.IntStream

private const val DAY = "day03";
fun main() {

    data class Shadow(val x: Int, val y:Int)


    fun initShadoMap(file: List<String>) : Set<Shadow> {
        val shadowMap: MutableSet<Shadow> = mutableSetOf()
        IntStream.range(0, file.size).forEach{ y ->
            val line = file[y];
            println(line)
            if(line.contains(Regex("[^a-zA-Z0-9.]"))){
                println("hit")
                IntStream.range(0, line.length).forEach {x ->
                    if(Regex("[^a-zA-Z0-9.]").matches(line[x].toString())){
                        println("hit-hit")
                        IntStream.of(-1, 0 ,1 ).forEach { ox ->
                            IntStream.of(-1, 0 ,1 ).forEach { oy ->
                                shadowMap.add(Shadow(x+ox,y+oy));
                            }
                        }
                    }
                }
            }
        }
        println(shadowMap)
        return shadowMap;
    }

    fun part1(file: List<String>): Int {
        val shadowMap = initShadoMap(file);
        val newMap : MutableList<MutableList<Char>> = MutableList(file.size) { MutableList(file[0].length) {'.'} }
        var sum = 0;
        IntStream.range(0, file.size).forEach{ y ->
            val line = file[y];
            newMap.plus(line.toCharArray());
            var number = 0;
            var hit = false;
            IntStream.range(0,line.length).forEach {x ->
                if(line[x].isDigit()){
                    number = number*10 + line[x].digitToInt();
                    if(shadowMap.contains(Shadow(x,y))){
                        hit = true;
                    }
                } else {
                    if(number > 0){
                        if(hit){
                            sum += number
                            println(-number)
                        } else {
                            println(number)
                        }
                        number = 0;
                        hit = false
                    }
                }
                if(x == line.length -1 && number > 0){
                        if(hit){
                            sum += number
                            println(-number)
                        } else {
                            println(number)
                        }
                        number = 0;
                        hit = false
                    }
            }
        }
        println(sum)
        return sum;
    }

    class Gear(val x: Int, val y:Int, var num1:Int, var num2:Int, var isValid: Boolean) {
        fun isValid(ox : Int,oy :Int) :Boolean{
            if(ox > x-2 && ox < x+2 && oy > y-2 && oy < y+2){
                    return true;
                }
            return false
        }
        fun addNum(num : Int){
            if(num1 == 0 ){
                num1 = num;
                return
            }
            if(num2 == 0){
                num2 = num
                return
            }
            isValid = false
            return
        }

        fun gearRatio() : Int{
            return num1 * num2
        }
    }

    fun initGearMap(file: List<String>) : Set<Gear> {
        val shadowMap: MutableSet<Gear> = mutableSetOf()
        IntStream.range(0, file.size).forEach{ y ->
            val line = file[y];
            println(line)
            if(line.contains("*")){
                println("hit")
                IntStream.range(0, line.length).forEach {x ->
                    if(line[x] == '*'){
                        println("hit-hit")
                        shadowMap.add(Gear(x,y,0,0, true));
                    }
                }
            }
        }
        println(shadowMap)
        return shadowMap;
    }

    fun part2(file: List<String>): Int {
        val shadowMap = initGearMap(file);
        val newMap : MutableList<MutableList<Char>> = MutableList(file.size) { MutableList(file[0].length) {'.'} }
        IntStream.range(0, file.size).forEach{ y ->
            val line = file[y];
            newMap.plus(line.toCharArray());
            var number = 0;
            var g : Gear? = null;
            IntStream.range(0,line.length).forEach {x ->
                if(line[x].isDigit()){
                    number = number*10 + line[x].digitToInt();
                    if(g == null){
                        g = shadowMap.firstOrNull() {it.isValid(x,y) }
                    }
                } else {
                    if(number > 0){
                        if(g != null){
                            g!!.addNum(number)
                            println(-number)
                        } else {
                            println(number)
                        }
                        number = 0;
                        g = null
                    }
                }
                if(x == line.length -1 && number > 0){
                        if(g != null){
                            g!!.addNum(number)
                            println(-number)
                        } else {
                            println(number)
                        }
                        number = 0;
                        g = null
                    }
            }
        }
        val sum = shadowMap.stream().filter(Gear::isValid).map(Gear::gearRatio).reduce{ acc, next -> acc + next}
        println(sum)
        return sum.orElse(0);
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(DAY, true)
    check(part1(testInput) == 4361)
    val input = readInput(DAY, false)
    part1(input).println()
    println("---NEXT PART---")
    check(part2(testInput) == 467835)
    part2(input).println()
}
