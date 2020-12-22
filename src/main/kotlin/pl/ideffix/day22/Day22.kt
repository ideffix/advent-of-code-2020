package pl.ideffix.day22

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day22/input.txt")
    val pair = parseInput(lines)
    println(solveFirst(pair))
}

fun solveFirst(pair: Pair<ArrayDeque<Int>, ArrayDeque<Int>>): Int {
    while (pair.first.isNotEmpty() && pair.second.isNotEmpty()) {
        if (pair.first.first() > pair.second.first()) {
            with(pair.first) {
                addLast(first())
                addLast(pair.second.first())
            }
        } else {
            with(pair.second) {
                addLast(first())
                addLast(pair.first.first())
            }
        }
        pair.first.removeFirst()
        pair.second.removeFirst()
    }
    var count = 0
    val notEmpty = if (pair.first.isNotEmpty()) pair.first else pair.second
    for (i in 0 until notEmpty.size) {
        count += notEmpty[i] * (notEmpty.size - i)
    }
    return count
}

fun parseInput(lines: List<String>): Pair<ArrayDeque<Int>, ArrayDeque<Int>> {
    var firstDeck = ArrayDeque<Int>()
    var currentDeck = ArrayDeque<Int>()
    for (line in lines) {
        if (line.startsWith("Player")) continue
        if (line.isNullOrBlank()) {
            firstDeck = currentDeck
            currentDeck = ArrayDeque()
        } else {
            currentDeck.add(line.toInt())
        }
    }
    return firstDeck to currentDeck
}

