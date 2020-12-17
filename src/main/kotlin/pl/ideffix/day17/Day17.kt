package pl.ideffix.day17

import pl.ideffix.utils.FileUtils

const val cycles = 6

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day17/input.txt").map { it.toCharArray().toList() }
    val matrix = createInitialMatrix(lines)
    println(solveSecond(matrix))
}

fun createInitialMatrix(lines: List<List<Char>>): List<List<List<List<Char>>>> {
    val size = lines.size + (cycles+1) * 2
    val matrix = createEmptyMatrix(size)
    val mid  = size / 2
    for (i in lines.indices) {
        for (j in lines.indices) {
            matrix[mid][mid][i+ cycles+1][j+ cycles+1] = lines[i][j]
        }
    }
    return matrix
}

fun createEmptyMatrix(size: Int): MutableList<MutableList<MutableList<MutableList<Char>>>> {
    val matrix = mutableListOf<MutableList<MutableList<MutableList<Char>>>>()
    for (w in 0 until size) {
        val iList = mutableListOf<MutableList<MutableList<Char>>>()
        for (i in 0 until size) {
            val jList = mutableListOf<MutableList<Char>>()
            for (j in 0 until size) {
                val kList = mutableListOf<Char>()
                for (k in 0 until size) {
                    kList.add('.')
                }
                jList.add(kList)
            }
            iList.add(jList)
        }
        matrix.add(iList)
    }
    return matrix
}

fun solveSecond(matrix: List<List<List<List<Char>>>>): Int {
    var currentMatrix = matrix
    val size = matrix.size
    for (c in 0 until cycles) {
        val newMatrix = createEmptyMatrix(matrix.size)
        for (w in 1 until size - 1) {
            for (z in 1 until size - 1) {
                for (y in 1 until size - 1) {
                    for (x in 1 until size - 1) {
                        var active = 0
                        for (i in -1..1) {
                            for (j in -1..1) {
                                for (k in -1..1) {
                                    for (l in -1..1) {
                                        if (i or j or k or l == 0) continue
                                        if (currentMatrix[w + i][z + j][y + k][x + l] == '#') active++
                                    }
                                }
                            }
                        }
                        when (currentMatrix[w][z][y][x]) {
                            '#' -> {
                                if (active == 2 || active == 3) {
                                    newMatrix[w][z][y][x] = '#'
                                }
                            }
                            '.' -> {
                                if (active == 3) {
                                    newMatrix[w][z][y][x] = '#'
                                }
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

fun countActiveCubes(matrix: List<List<List<List<Char>>>>): Int =
    matrix.flatten().flatten().flatten().count { it == '#' }

