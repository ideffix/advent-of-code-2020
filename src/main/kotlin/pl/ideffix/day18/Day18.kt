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
    val nums = mutableListOf<Long>()

    var currentSum = 0L
    for (i in 1 until woBrackets.size step 2) {
        currentSum += woBrackets[i-1].toLong()
        if (woBrackets[i] == "*") {
            nums.add(currentSum)
            currentSum = 0
        }
    }
    nums.add(currentSum + woBrackets.last().toLong())
    return nums.fold(1, {acc, l -> acc * l })
}
