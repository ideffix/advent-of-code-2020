package pl.ideffix.day2

import pl.ideffix.utils.FileUtils

fun main() {
    val result = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day2/input.txt").stream()
        .map { it.split(" ") }
        .filter {
            val range = it[0].split('-')
            PasswordPolicy(range[0].toInt(), range[1].toInt(), it[1][0]).performCheck2(it[2])
        }
        .count()
    println(result)
}