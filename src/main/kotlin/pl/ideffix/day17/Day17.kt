package pl.ideffix.day17

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day17/input.txt")
    val matrix = mutableListOf(createEmptyLayer(lines[0].length, lines.size), lines, createEmptyLayer(lines[0].length, lines.size))
    print(matrix)
}

fun createEmptyLayer(x: Int, y: Int): List<String> {
    val l = mutableListOf<String>()
    for (j in 0 until y) {
        l.add(".".repeat(x))
    }
    return l
}