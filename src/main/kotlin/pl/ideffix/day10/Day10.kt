package pl.ideffix.day10

import pl.ideffix.utils.FileUtils
import kotlin.streams.toList

fun main() {
    val nums = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day10/input.txt")
            .stream()
            .map{it.toLong()}
            .sorted()
            .toList()

    val map = mutableMapOf<Int, Long>()
    println(countWays(0, nums, map))
}

fun countWays(startIndex: Int, nums: List<Long>, map: MutableMap<Int, Long>): Long {
    return when {
        nums.size - 1 == startIndex -> 1
        map[startIndex] != null -> map[startIndex]!!
        else -> {
            var count = 0L
            var endRange = if (nums.size - 1 - startIndex > 3) 3 else  nums.size - 1 - startIndex
            for (i in 1..endRange) {
                if (nums[startIndex + i] - nums[startIndex] <= 3) {
                    count += countWays(startIndex + i, nums, map)
                }
            }
            map[startIndex] = count
            count
        }
    }
}