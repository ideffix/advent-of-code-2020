package pl.ideffix.day13

import pl.ideffix.utils.FileUtils
import kotlin.streams.toList

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day13/input.txt")
    println(secondTask(lines[1]))
}

fun secondTask(input: String): Long {
    val split = input.split(',')
    val pairs = mutableListOf<Pair<Long, Long>>()
    for (i in split.indices) {
        if (split[i] == "x") continue
        pairs.add(split[i].toLong() to i.toLong())
    }

    var increment = pairs[0].first
    var busIndex = 1
    var i = pairs[0].first
    while(busIndex < pairs.size) {
        if ((i + pairs[busIndex].second) % pairs[busIndex].first == 0L) {
            increment *= pairs[busIndex].first;
            busIndex++;
        }
        i += increment
    }
    return i - increment;
}

fun firstTask(lines: List<String>): Int {
    val timestamp = lines[0].toInt()
    val ids = lines[1].split(',').stream()
        .filter {it != "x"}
        .map {it.toInt()}
        .toList()
    var resId = -1
    var resTimeToWait = Integer.MAX_VALUE
    for (id in ids) {
        val diff = (timestamp / id + 1) * id - timestamp
        if (diff < resTimeToWait) {
            resTimeToWait = diff
            resId = id
        }
    }
    return resId * resTimeToWait
}
