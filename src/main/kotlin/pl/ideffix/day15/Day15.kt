package pl.ideffix.day15

import pl.ideffix.utils.FileUtils

fun main() {
    val nums = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day15/input.txt")[0].split(',').map { it.toInt() }
    println(firstTask(nums.toMutableList(), 30000000))
}

fun firstTask(nums: MutableList<Int>, end: Int): Int {
    val lastUsed = mutableMapOf<Int, MutableList<Int>>()
    for (i in nums.indices) {
        val arr = lastUsed.getOrDefault(nums[i], ArrayDeque())
        arr.add(0, i)
        lastUsed[nums[i]] = arr
    }
    var i = nums.size
    while (i < end) {
        val prev = nums.last()
        var arr = lastUsed.getOrDefault(prev, ArrayDeque())
        var current = 0
        if (arr.size >= 2) {
            current = arr[0] - arr[1]
        }
        arr = lastUsed.getOrDefault(current, ArrayDeque())
        arr.add(0, i)
        nums.add(current)
        lastUsed[current] = arr
        i++
    }
    return nums.last()
}
