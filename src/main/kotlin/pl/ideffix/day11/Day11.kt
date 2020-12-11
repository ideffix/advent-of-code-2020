package pl.ideffix.day11

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day11/input.txt")
    val board = wrap(lines)
    val res = gameOfSeats(board)
    println(res.joinToString("").count { it == '#' })
}

fun gameOfSeats(board: MutableList<String>): List<String> {
    var stateChanged = true
    var b = mutableListOf<String>()
    var currentBoard = board
    while(stateChanged) {
        stateChanged = false
        b = mutableListOf()
        for (i in 1..currentBoard.size - 2) {
            var l = ""
            for (j in 1..currentBoard[0].length - 2) {
                l += when {
                    canBeOccupied(currentBoard, i, j) -> {
                        stateChanged = true
                        '#'
                    }
                    canBeEmpty(currentBoard, i, j) -> {
                        stateChanged = true
                        'L'
                    }
                    else -> currentBoard[i][j]
                }
            }
            b.add(l)
        }
        currentBoard = wrap(b)
    }
    return currentBoard
}

fun canBeEmpty(board: MutableList<String>, i: Int, j: Int): Boolean {
    if (board[i][j] == '#') {
        var count = 0
        for (x in -1..1) {
            for (y in -1..1) {
                if (x == 0 && y == 0) continue
                if (board[i + x][j + y] == '#') count++
            }
        }
        return count >= 4
    }
    return false
}

fun canBeOccupied(board: MutableList<String>, i: Int, j: Int): Boolean {
    if (board[i][j] == 'L') {
        for (x in -1..1) {
            for (y in -1..1) {
                if (x == 0 && y == 0) continue
                if (board[i + x][j + y] == '#') return false
            }
        }
        return true
    }
    return false
}

fun wrap(lines: List<String>): MutableList<String> {
    var board = lines.toMutableList()
    val str = ".".repeat(board[0].length)
    board.add(0, str)
    board.add(str)
    board = board.map { ".${it}." } .toMutableList()
    return board
}