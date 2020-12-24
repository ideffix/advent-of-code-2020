package pl.ideffix.day24

import pl.ideffix.utils.FileUtils

val dirsMove = mapOf(
        "e" to (0 to 2),
        "se" to (-1 to 1),
        "sw" to (-1 to -1),
        "w" to (0 to -2),
        "nw" to (1 to -1),
        "ne" to (1 to 1)
)

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day24/input.txt")
    val dirsList = parseInput(lines)
    val blacks = solveFirst(dirsList)
    println(blacks.size)
    println(solveSecond(blacks))
}

fun solveSecond(blacks: Set<Pair<Int, Int>>): Int {
    var startDay = blacks.toMutableSet()
    for (i in 0 until 100) {
        var endDay = mutableSetOf<Pair<Int, Int>>()
        for (point in startDay) {
            val blackNeighbours = countBlackNeighbours(point, startDay)
            endDay.addAll(checkWhiteNeighbours(point, startDay))
            if (blackNeighbours == 0 || blackNeighbours > 2) continue
            endDay.add(point)
        }
        println("Day ${i + 1} ${endDay.size}")
        startDay = endDay
    }
    return startDay.size
}

fun checkWhiteNeighbours(point: Pair<Int, Int>, startDay: MutableSet<Pair<Int, Int>>): Collection<Pair<Int, Int>> {
    val blacks = mutableSetOf<Pair<Int, Int>>()
    for (dir in dirsMove.values) {
        val p = point.first + dir.first to point.second + dir.second
        if (startDay.contains(p)) continue
        val blackCount = countBlackNeighbours(p, startDay)
        if (blackCount == 2) blacks.add(p)
    }
    return blacks
}

fun countBlackNeighbours(point: Pair<Int, Int>, startDay: MutableSet<Pair<Int, Int>>): Int =
   dirsMove.values.fold(0, {acc, dir ->
       val p = point.first + dir.first to point.second + dir.second
       if (startDay.contains(p)) acc + 1 else acc
   } )


fun solveFirst(dirsList: List<List<String>>): Set<Pair<Int, Int>> {
    val blacks = mutableSetOf<Pair<Int, Int>>()
    for (dirs in dirsList) {
        blacks.addOrRemove(dirs.fold(0 to 0, {acc, s ->
            val el = dirsMove[s] ?: error("Zly parsing")
            acc.first + el.first to acc.second + el.second
        }))
    }
    return blacks
}

private fun <E> MutableSet<E>.addOrRemove(el: E) {
    if (contains(el)) {
        remove(el)
    } else {
        add(el)
    }
}

fun parseInput(lines: List<String>): List<List<String>> {
    val dirsList = mutableListOf<List<String>>()
    for (line in lines) {
        val dirs = mutableListOf<String>()
        var i = 0
        while (i < line.length) {
            if (line[i] == 's' || line[i] == 'n') {
                dirs.add(line[i].toString() + line[i+1])
                i += 2
                continue
            }
            dirs.add(line[i].toString())
            i++
        }
        dirsList.add(dirs)
    }
    return dirsList
}
