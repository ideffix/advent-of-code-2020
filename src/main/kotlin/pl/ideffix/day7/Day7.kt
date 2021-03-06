package pl.ideffix.day7

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day7/input.txt")
    val map = prepareStructure2(lines)
    print(countOccurrence2("shiny gold", map))
}

fun countOccurrence(bagName: String, map: Map<String, List<Pair<String, Int>>>, visited: MutableSet<String>): Int {
    var count = 0
    val bag = map[bagName] ?: return count
    for (b in bag) {
        if (visited.contains(b.first)) continue
        visited.add(b.first)
        count += countOccurrence(b.first, map, visited) + 1
    }
    return count
}

fun countOccurrence2(bagName: String, map: Map<String, List<Pair<String, Int>>>): Int {
    var count = 0
    val bag = map[bagName] ?: return count
    for (b in bag) {
        count += countOccurrence2(b.first, map) * b.second + b.second
    }
    return count
}

private fun prepareStructure(
    lines: List<String>
): MutableMap<String, MutableList<Pair<String, Int>>> {
    val map = mutableMapOf<String, MutableList<Pair<String, Int>>>()
    for (line in lines) {
        val split = line.split(' ')
        val name = split.take(2).joinToString(" ")
        val containedBags = split.subList(4, split.size).joinToString(" ").split(',')
        for (bag in containedBags) {
            val b = bag.trim().split(' ')
            val howMany = b[0]
            val bagName = b.subList(1, 3).joinToString(" ")
            if (howMany == "no") continue
            val mapEl = map[bagName]
            val pair = name to howMany.toInt()
            if (mapEl == null) {
                map[bagName] = mutableListOf(pair)
            } else {
                mapEl.add(pair)
            }
        }
    }
    return map
}

private fun prepareStructure2(
    lines: List<String>
): MutableMap<String, MutableList<Pair<String, Int>>> {
    val map = mutableMapOf<String, MutableList<Pair<String, Int>>>()
    for (line in lines) {
        val split = line.split(' ')
        val name = split.take(2).joinToString(" ")
        val containedBags = split.subList(4, split.size).joinToString(" ").split(',')
        for (bag in containedBags) {
            val b = bag.trim().split(' ')
            val howMany = b[0]
            val bagName = b.subList(1, 3).joinToString(" ")
            if (howMany == "no") continue
            val mapEl = map[name]
            val pair = bagName to howMany.toInt()
            if (mapEl == null) {
                map[name] = mutableListOf(pair)
            } else {
                mapEl.add(pair)
            }
        }
    }
    return map
}