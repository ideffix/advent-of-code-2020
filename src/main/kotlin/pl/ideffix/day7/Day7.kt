package pl.ideffix.day7

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day7/input.txt")
    for (line in lines) {
        val split = line.split(' ')
        val name = split.take(2).joinToString(" ")
        val containedBags = split.subList(4, split.size).joinToString(" ").split(',')
        

        println(containedBags)
    }
}