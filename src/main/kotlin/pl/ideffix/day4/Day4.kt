package pl.ideffix.day4

import pl.ideffix.utils.FileUtils

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
                        "byr" -> pass.byr = split[1]
                        "iyr" -> pass.iyr = split[1]
                        "eyr" -> pass.eyr = split[1]
                        "hgt" -> pass.hgt = split[1]
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