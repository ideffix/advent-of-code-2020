import pl.ideffix.day1.findSumOfTwo
import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day9/input.txt")
    val nums = lines.map{it.toLong()}.toList()
    val range = 25
    var toFind = -1L
    for (i in range until nums.size) {
        val res = findSumOfTwo(nums.subList(i-range, i), nums[i])
        if (res == null) {
            toFind = nums[i]
        }
    }
    var sum = 0L
    var arr = mutableListOf<Long>()
    for (num in nums) {
        arr.add(num)
        sum += num
        while (sum > toFind) {
            sum -= arr.removeFirst()
        }
        if (sum == toFind) {
            println(arr.min()!! + arr.max()!!)
            break
        }
    }

}