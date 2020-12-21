package pl.ideffix.day21

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day21/input.txt")
    val allergens = parseInput(lines)
    println(solveFirst(allergens))
}

fun solveFirst(allergens: List<Pair<String, MutableList<MutableList<String>>>>): Int {
    var wasChange = true
    while (wasChange) {
        wasChange = false
        allergens.forEach { pair ->
            if (pair.second.isNotEmpty()) {
                var common = pair.second.first().toSet()
                for (i in 1 until pair.second.size) {
                    common = common.intersect(pair.second[i])
                }
                if (common.size == 1) {
                    wasChange = true
                    allergens.forEach { pair -> pair.second.forEach {it.remove(common.first())} }
                }
            }
        }
    }
    val allergenSet = mutableSetOf<List<String>>()
    allergens.forEach { pair ->
        pair.second.forEach { allergenSet.add(it) }
    }

    return allergenSet.fold(0, {acc, list -> acc + list.size })
}

fun parseInput(lines: List<String>): List<Pair<String, MutableList<MutableList<String>>>> {
    val map = mutableMapOf<String, MutableList<MutableList<String>>>()
    lines.forEach { line ->
        val split = line.split('(')
        val allergens = split[1].trim().replace("contains ", "").replace(")", "").split(",").map { it.trim() }
        val ingredients = split[0].trim().split(" ").map { it.trim() }.toMutableList()
        allergens.forEach { allergen ->
            val l = map.getOrDefault(allergen, mutableListOf())
            l.add(ingredients)
            map[allergen] = l
        }
    }
    return map.toList()
}
