package pl.ideffix.day14

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day14/input.txt")
    var mask = ""
    val map = mutableMapOf<Long, Long>()
    for (line in lines) {
        val split = line.split(" = ")
        if (line.startsWith("mask")) {
            mask = split[1]
        } else {
            val address = split[0].substringAfter('[').substringBefore(']').toLong()
            val value = split[1].toLong()
            map[address] = orMask(mask, andMask(mask, value))
        }
    }
    var sum = 0L
    for (entry in map) {
        sum += entry.value
    }
    println(sum)
}

fun andMask(mask: String, value: Long): Long {
    return mask.map { when(it) {
        'X' -> '1'
        else -> it
    } }.joinToString("").toLong(2) and value
}

fun orMask(mask: String, value: Long): Long {
    return mask.map { when(it) {
        'X' -> '0'
        else -> it
    } }.joinToString("").toLong(2) or value
}
