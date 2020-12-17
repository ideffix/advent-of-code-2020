package pl.ideffix.day17

import pl.ideffix.utils.FileUtils

const val cycles = 6

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day17/input.txt").map { it.toCharArray().toList() }
    val matrix = createInitialMatrix(lines)
    println(solveFirst(matrix))
}

fun createInitialMatrix(lines: List<List<Char>>): List<List<List<Char>>> {
    val size = lines.size + (cycles+1) * 2
    val matrix = createEmptyMatrix(size)
    val mid  = size / 2
    for (i in lines.indices) {
        for (j in lines.indices) {
            matrix[mid][i+ cycles+1][j+ cycles+1] = lines[i][j]
        }
    }
    return matrix
}

fun createEmptyMatrix(size: Int): MutableList<MutableList<MutableList<Char>>> {
    val matrix = mutableListOf<MutableList<MutableList<Char>>>()
    for (i in 0 until size) {
        val jList = mutableListOf<MutableList<Char>>()
        for (j in 0 until size) {
            val kList = mutableListOf<Char>()
            for (k in 0 until size) {
                kList.add('.')
            }
            jList.add(kList)
        }
        matrix.add(jList)
    }
    return matrix
}

fun solveFirst(matrix: List<List<List<Char>>>): Int {
    var currentMatrix = matrix
    val size = matrix.size
    for (c in 0 until cycles) {
        val newMatrix = createEmptyMatrix(matrix.size)
        for (z in 1 until size - 1) {
            for (y in 1 until size - 1) {
                for (x in 1 until size - 1) {
                    var active = 0
                    for (i in -1..1) {
                        for (j in -1..1) {
                            for (k in -1..1) {
                                if (i or j or k == 0) {
                                    continue
                                }
                                if (currentMatrix[z+i][y+j][x+k] == '#') active++
                            }
                        }
                    }
                    when (currentMatrix[z][y][x]) {
                        '#' -> {
                            if (active == 2 || active == 3) {
                                newMatrix[z][y][x] = '#'
                            }
                        }
                        '.' -> {
                            if (active == 3) {
                                newMatrix[z][y][x] = '#'
                            }
                        }
                    }
                }
            }
        }
        currentMatrix = newMatrix
    }

    return countActiveCubes(currentMatrix)
}

fun countActiveCubes(matrix: List<List<List<Char>>>): Int =
    matrix.flatten().flatten().count { it == '#' }

