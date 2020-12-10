package pl.ideffix.day10

import pl.ideffix.utils.FileUtils
import kotlin.streams.toList

fun main() {
    val nums = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day10/input.txt")
            .stream()
            .map{it.toInt()}
            .sorted()
            .toList()

    val map = mutableMapOf(1 to 1, 3 to 1)
    for (i in 1 until nums.size) {
        val diff = nums[i] - nums[i-1]
        val mapEl = map[diff]
        if (mapEl == null) {
            map[diff] = 1
        } else {
            map[diff] = mapEl + 1
        }
    }
    println(map[1]!! * map[3]!!)
}