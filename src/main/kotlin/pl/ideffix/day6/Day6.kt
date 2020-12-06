package pl.ideffix.day6

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day6/input.txt")
    println(first(lines))
    println(second(lines))
}

fun first(lines: List<String>): Int {
    val set = mutableSetOf<Char>()
    var count = 0
    for (line in lines) {
        if (line.isNullOrEmpty()) {
            count += set.size
            set.clear()
        } else {
            for (c in line) {
                set.add(c)
            }
        }
    }
    count += set.size
    return count
}

fun second(lines: List<String>): Int {
    val map = mutableMapOf<Char, Int>()
    var count = 0
    var groupCount = 0
    for (line in lines) {
        if (line.isNullOrEmpty()) {
            map.forEach {
                if (it.value == groupCount) {
                    count++
                }
            }
            map.clear()
            groupCount = 0
        } else {
            groupCount++
            for (c in line) {
                val charCount = map[c]
                if (charCount != null) {
                    map[c] = charCount + 1
                } else {
                    map[c] = 1
                }
            }
        }
    }
    map.forEach {
        if (it.value == groupCount) {
            count++
        }
    }
    return count
}