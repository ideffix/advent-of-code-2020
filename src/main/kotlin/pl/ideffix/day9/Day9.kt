import pl.ideffix.day1.findSumOfTwo
import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day9/input.txt")
    val nums = lines.map{it.toLong()}.toList()
    val range = 25
    for (i in range until nums.size) {
        val res = findSumOfTwo(nums.subList(i-range, i), nums[i])
        if (res == null) {
            println(nums[i])
        }
    }

}