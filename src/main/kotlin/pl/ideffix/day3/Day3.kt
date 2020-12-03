package pl.ideffix.day3

import pl.ideffix.utils.FileUtils

val coordinates = listOf(
        1 to 1,
        3 to 1,
        5 to 1,
        7 to 1,
        1 to 2
)

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day3/input.txt")
    val result = coordinates.stream()
            .map { treesCount(lines, it.first, it.second).toLong() }
            .reduce (1) { acc, el -> el * acc }
    println(result)
}

fun treesCount(lines: List<String>, right: Int, down: Int): Int {
    var j = 0
    var count = 0
    for (i in down until lines.size step down) {
        j = (j + right) % lines[0].length
        if (lines[i][j] == '#') count++;
    }
    return count
}