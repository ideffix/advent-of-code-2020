package pl.ideffix.day17

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day17/input.txt")
    val matrix = listOf(createEmptyLayer(lines[0].length, lines.size), lines, createEmptyLayer(lines[0].length, lines.size))
    println(solveFirst(matrix))
}

fun solveFirst(matrix: List<List<String>>): Int {
    var currentMatrix = matrix.toMutableList()
    val x = matrix[0][0].length
    val y = matrix[0].size
    for (cycle in 0 until 6) {
        val newMatrix = mutableListOf<List<String>>()
        if (!isEmptyLayer(currentMatrix.first())) {
            currentMatrix.add(0, createEmptyLayer(x, y))
        }
        if (!isEmptyLayer(currentMatrix.last())) {
            currentMatrix.add(createEmptyLayer(x, y))
        }
        for (l in matrix.indices) {
            newMatrix.add(checkNewLayerState(matrix, l))
        }
        currentMatrix = newMatrix
    }
    return countActiveCubes(currentMatrix)
}

fun countActiveCubes(matrix: MutableList<List<String>>): Int {
    TODO("Not yet implemented")
}

fun checkNewLayerState(matrix: List<List<String>>, l: Int): List<String> {
    TODO("Not yet implemented")
}

fun isEmptyLayer(layer: List<String>): Boolean {
    TODO("Not yet implemented")
}

fun createEmptyLayer(x: Int, y: Int): List<String> {
    val l = mutableListOf<String>()
    for (j in 0 until y) {
        l.add(".".repeat(x))
    }
    return l
}