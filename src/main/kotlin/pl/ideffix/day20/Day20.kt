package pl.ideffix.day20

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day20/input.txt")
    val puzzles = parseInput(lines)
    val edgesMap = mutableMapOf<String, MutableList<Int>>()
    for (tile in puzzles) {
        val allCombinations = tile.getAllEdgesCombinations()
        for (edge in allCombinations) {
            val ids = edgesMap.getOrDefault(edge, mutableListOf())
            ids.add(tile.id)
            edgesMap[edge] = ids
        }
    }
    val countMap = mutableMapOf<Int, Int>()
    val filtered = edgesMap.filter { it.value.size == 1 }
    for (entry in filtered) {
        var count = countMap.getOrDefault(entry.value[0], 0)
        countMap[entry.value[0]] = ++count
    }
    val res = countMap.filter { it.value == 4 }.keys.fold(1L, {acc, count -> acc * count })
    println(res)
}

fun parseInput(lines: List<String>): List<Tile> {
    val puzzleElements = mutableListOf<Tile>()
    var mode = 0
    var id = -1
    var puzzle = mutableListOf<String>()
    for (line in lines) {
        if (line.isNullOrEmpty()) {
            mode = 0
            puzzleElements.add(Tile(puzzle, id))
            puzzle = mutableListOf()
            continue
        }
        when(mode) {
            0 ->  {
                id = line.replace("Tile ", "").replace(":", "").toInt()
                mode = 1
            }
            1 -> puzzle.add(line)
        }
    }
    puzzleElements.add(Tile(puzzle, id))

    return puzzleElements
}

class Tile(private val puzzle: List<String>, val id: Int) {
    fun getTop(): String = puzzle.first()

    fun getBottom(): String = puzzle.last()

    fun getLeft(): String = puzzle.fold("", {acc, line -> acc + line.first() })

    fun getRight(): String = puzzle.fold("", {acc, line -> acc + line.last() })

    fun getAllEdgesCombinations(): List<String> =
        listOf(
            getTop(),
            getTop().reversed(),
            getBottom(),
            getBottom().reversed(),
            getLeft(),
            getLeft().reversed(),
            getRight(),
            getRight().reversed()
        )
}