package pl.ideffix.day14

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day14/input.txt")
    println(v1(lines))
    println(v2(lines))
}

fun v2(lines: List<String>): Long {
    var mask = ""
    val map = mutableMapOf<Long, Long>()
    for (line in lines) {
        val split = line.split(" = ")
        if (line.startsWith("mask")) {
            mask = split[1]
        } else {
            val address = split[0].substringAfter('[').substringBefore(']').toLong()
            val value = split[1].toLong()
            val masked = maskAddress(address, mask)
            writeToMem(masked, value, map)
        }
    }
    var sum = 0L
    for (entry in map) {
        sum += entry.value
    }
    return sum
}

fun writeToMem(masked: String, value: Long, map: MutableMap<Long, Long>) {
    if (!masked.contains('X')) {
        map[masked.toLong(2)] = value
        return
    }
    writeToMem(masked.replaceFirst('X', '0'), value, map)
    writeToMem(masked.replaceFirst('X', '1'), value, map)
}

fun maskAddress(address: Long, mask: String): String {
    var adrBit = address.toString(2)
    adrBit = adrBit.padStart(36, '0')
    var masked = ""
    for (i in 0 until 36) {
        masked += when(mask[i]) {
            '0' -> adrBit[i]
            else -> mask[i]
        }
    }
    return masked
}

fun v1(lines: List<String>): Long {
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
    return sum
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
