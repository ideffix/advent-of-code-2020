package pl.ideffix.day12

import pl.ideffix.utils.FileUtils
import kotlin.math.abs

val positions = listOf('N', 'E', 'S', 'W')

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day12/input.txt")

    var currentPos = 'E'
    var x = 0
    var y = 0
    for (line in lines) {
        val command = line[0]
        val argument = line.substring(1).toInt()
        when(command) {
            'N' -> y += argument
            'S' -> y -= argument
            'E' -> x += argument
            'W' -> x -= argument
            'L', 'R' -> currentPos = changePos(currentPos, command, argument)
            'F' -> {
                when (currentPos) {
                    'N' -> y += argument
                    'S' -> y -= argument
                    'E' -> x += argument
                    'W' -> x -= argument
                }
            }
        }
    }
    val res = abs(x) + abs(y)
    println(res)
}

fun changePos(pos: Char, change: Char, angle: Int): Char {
    val changeVal = angle / 90
    val currentIndex = positions.indexOf(pos)
    var move = if (change == 'R') changeVal else -changeVal
    if (currentIndex + move % positions.size < 0) move = positions.size + move % positions.size
    return positions[(positions.indexOf(pos) + move) % positions.size]
}