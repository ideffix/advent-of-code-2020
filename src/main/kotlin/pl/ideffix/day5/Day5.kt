package pl.ideffix.day5

import pl.ideffix.utils.FileUtils
import kotlin.streams.toList

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day5/input.txt")
    println(findYourSeatId(lines))
}

fun findHighestId(lines: List<String>): Int {
    return lines.stream()
        .map(::mapToSeat)
        .map(::countId)
        .toList().maxOrNull() ?: -1

}

fun findYourSeatId(lines: List<String>): Int? {
    val sorted = lines.stream()
        .map(::mapToSeat)
        .map(::countId)
        .sorted()
        .toList()

    for (i in 1..sorted.size) {
        if (sorted[i] - sorted[i-1] != 1) {
            return sorted[i] - 1
        }
    }
    return null
}

fun mapToSeat(str: String): Pair<Int, Int> {
    val row = str.take(7)
    val col = str.takeLast(3)
    return findPos(row, 'F', 'B', 0, 127) to findPos(col, 'L', 'R', 0, 7)
}

fun findPos(str: String, highChar: Char, lowChar: Char, low: Int, high: Int): Int {
    var h = high
    var l = low
    var res = -1
    for (c in str) {
        var mid = (h + l) / 2
        if (highChar == c) {
            h = mid
            res = h
        } else if (lowChar == c) {
            l = mid + 1
            res = l
        }
    }
    return res
}

fun countId(seat: Pair<Int, Int>): Int = seat.first * 8 + seat.second
