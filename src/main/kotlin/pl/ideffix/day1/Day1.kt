package pl.ideffix.day1

import pl.ideffix.utils.FileUtils
import kotlin.streams.toList

fun main() {
    val nums = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day1/input.txt").stream().map { it.toLong() }.toList()
    val pair = findSumOfTwo(nums, 2020)
    if (pair != null) {
        println(pair.first * pair.second)
    }
    val triple = findSumOfThree(nums, 2020)
    if (triple != null) {
        println(triple.first * triple.second * triple.third)
    }
}

fun findSumOfThree(nums: List<Long>, toFind: Long): Triple<Long, Long, Long>? {
    for (num in nums) {
        val pair = findSumOfTwo(nums, toFind - num)
        if (pair != null) {
            return Triple(pair.first, pair.second, num)
        }
    }
    return null
}

fun findSumOfTwo(nums: List<Long>, toFind: Long): Pair<Long, Long>? {
    val complements = mutableListOf<Long>()
    for (num in nums) {
        if (num in complements) {
            return num to toFind - num
        }
        complements.add(toFind - num)
    }
    return null
}
