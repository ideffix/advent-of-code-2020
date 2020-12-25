package pl.ideffix.day25

import pl.ideffix.utils.FileUtils

val subject = 7L
val devide = 20201227L

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day25/input.txt")
    val keys = lines.map { it.toLong() }
    val l2 = getLoopSize(keys[1], subject)
    println(encrypt(keys[0], l2))
}

fun getLoopSize(key: Long, subject: Long): Int {
    var res = 1L
    var i = 0
    while (res != key) {
        res = (res * subject) % devide
        i++
    }
    return i
}

fun encrypt(subject: Long, loopSize: Int): Long {
    var res = 1L
    for (i in 0 until loopSize) {
        res = (res * subject) % devide
    }
    return res
}
