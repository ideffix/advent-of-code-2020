package pl.ideffix.day13

import pl.ideffix.utils.FileUtils
import kotlin.streams.toList

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day13/input.txt")
    println(firstTask(lines))
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
