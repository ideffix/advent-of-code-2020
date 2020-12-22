package pl.ideffix.day22

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day22/input.txt")
    val pair = parseInput(lines)
    //println(solveFirst(pair))
    println(solveSecond(pair))
}

fun solveSecond(pair: Pair<ArrayDeque<Int>, ArrayDeque<Int>>): Int {
    val notEmpty = if (playSubGame(pair)) pair.first else pair.second
    var count = 0
    for (i in 0 until notEmpty.size) {
        count += notEmpty[i] * (notEmpty.size - i)
    }
    return count
}

fun playSubGame(pair: Pair<ArrayDeque<Int>, ArrayDeque<Int>>): Boolean {
    val states = mutableSetOf<Int>()
    while (pair.first.isNotEmpty() && pair.second.isNotEmpty()) {
        if (!states.add(pair.hashCode())) {
            return true
        }
        val firstCard = pair.first.first()
        val secondCard = pair.second.first()
        pair.first.removeFirst()
        pair.second.removeFirst()
        val firstWon = if (pair.first.size >= firstCard && pair.second.size >= secondCard) {
            playSubGame(ArrayDeque(pair.first.subList(0, firstCard)) to ArrayDeque(pair.second.subList(0, secondCard)))
        } else {
            firstCard > secondCard
        }
        if (firstWon) {
            with(pair.first) {
                addLast(firstCard)
                addLast(secondCard)
            }
        } else {
            with(pair.second) {
                addLast(secondCard)
                addLast(firstCard)
            }
        }
    }
    return pair.first.isNotEmpty()
}

//fun solveFirst(pair: Pair<ArrayDeque<Int>, ArrayDeque<Int>>): Int {
//    var count = 0
//    val notEmpty = if (playSubGame(pair)) pair.first else pair.second
//    for (i in 0 until notEmpty.size) {
//        count += notEmpty[i] * (notEmpty.size - i)
//    }
//    return count
//}

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

