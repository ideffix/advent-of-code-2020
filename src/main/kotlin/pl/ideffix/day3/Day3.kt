package pl.ideffix.day3

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day3/input.txt")
    val down = 1
    val right = 3
    var j = 0
    var count = 0
    for (i in down until lines.size step down) {
        j = (j + right) % lines[0].length
        if (lines[i][j] == '#') count++;
    }
    println(count)
}