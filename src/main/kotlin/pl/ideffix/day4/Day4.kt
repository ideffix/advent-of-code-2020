package pl.ideffix.day4

import pl.ideffix.utils.FileUtils

val validHeightTypes = listOf("cm", "in")

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day4/input.txt")
    var pass = Password()
    var validPasswords = 0
    for (line in lines) {
        if (line.isNullOrBlank()) {
            if (pass.isValid()) validPasswords++
            pass = Password()
            continue
        }
        line.split(' ').stream()
                .forEach {
                    val split = it.split(':')
                    when (split[0]) {
                        "byr" -> pass.byr = split[1].toInt()
                        "iyr" -> pass.iyr = split[1].toInt()
                        "eyr" -> pass.eyr = split[1].toInt()
                        "hgt" -> pass.hgt = if (split[1].takeLast(2) in validHeightTypes) Height(split[1].take(split[1].length - 2).toInt(), split[1].takeLast(2)) else Height(-1, "bad")
                        "hcl" -> pass.hcl = split[1]
                        "ecl" -> pass.ecl = split[1]
                        "pid" -> pass.pid = split[1]
                        "cid" -> pass.cid = split[1]
                    }
                }
    }
    if (pass.isValid()) validPasswords++
    println(validPasswords)
}