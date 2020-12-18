package pl.ideffix.day18

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day18/input.txt")
    println(solveFirst(lines))
}

fun solveFirst(lines: List<String>): Long {
    var sum = 0L
    for (line in lines) {
        val split = line.replace(" ", "").split("").toMutableList().apply {
            removeFirst()
            removeLast()
        }
        sum += solve(split, 0, split.size)
    }
    return sum
}

fun solve(split: List<String>, start: Int, stop: Int): Long {
    val woBrackets = mutableListOf<String>()
    var i = start
    while(i < stop) {
        if (split[i] == "(") {
            val closingBracketIndex = findClosingBracket(split, i)
            woBrackets.add(solve(split, i+1, closingBracketIndex).toString())
            i = closingBracketIndex
        } else {
            woBrackets.add(split[i])
        }
        i++
    }

    return solveWOBrackets(woBrackets)
}

fun findClosingBracket(split: List<String>, start: Int): Int {
    var level = 0
    for (i in start until split.size) {
        when(split[i]) {
            "(" -> level++
            ")" -> level--
        }
        if (level == 0) {
            return i
        }
    }
    return -1
}

fun solveWOBrackets(woBrackets: MutableList<String>): Long {
    var res = woBrackets.first().toLong()
    for (i in 2 until woBrackets.size step 2) {
        when(woBrackets[i-1]) {
            "*" -> res *= woBrackets[i].toLong()
            "+" -> res += woBrackets[i].toLong()
        }
    }
    return res
}
