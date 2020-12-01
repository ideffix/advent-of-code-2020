package pl.ideffix.day1

import pl.ideffix.utils.FileUtils
import kotlin.streams.toList

fun main() {
    val nums = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day1/input.txt").stream().map { it.toInt() }.toList()
    val pair = findSumOf(nums, 2020)
    if (pair != null) {
        print(pair.first * pair.second)
    }
}

fun findSumOf(nums: List<Int>, toFind: Int): Pair<Int, Int>? {
    val complements = mutableListOf<Int>()
    for (num in nums) {
        if (num in complements) {
            return num to toFind - num
        }
        complements.add(toFind - num)
    }
    return null
}
