package pl.ideffix.day12

import pl.ideffix.utils.FileUtils
import kotlin.math.abs

val positions = listOf('N', 'E', 'S', 'W')

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day12/input.txt")
    println(firstTask(lines))
    println(secondTask(lines))
}

fun secondTask(lines: List<String>): Int {
    var waypoint = 10 to 1
    var pos = 0 to 0
    for (line in lines) {
        val command = line[0]
        val argument = line.substring(1).toInt()
        when (command) {
            'N' -> waypoint = waypoint.first to waypoint.second + argument
            'S' -> waypoint = waypoint.first to waypoint.second - argument
            'E' -> waypoint = waypoint.first + argument to waypoint.second
            'W' -> waypoint = waypoint.first - argument to waypoint.second
            'L', 'R' -> waypoint = changePos(waypoint, command, argument)
            'F' -> pos = pos.first + waypoint.first * argument to pos.second + waypoint.second * argument
        }
    }
    return abs(pos.first) + abs(pos.second)
}

fun firstTask(lines: List<String>): Int {
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
    return abs(x) + abs(y)
}

fun changePos(pos: Char, change: Char, angle: Int): Char {
    val changeVal = angle / 90
    val currentIndex = positions.indexOf(pos)
    var move = if (change == 'R') changeVal else -changeVal
    if (currentIndex + move % positions.size < 0) move = positions.size + move % positions.size
    return positions[(positions.indexOf(pos) + move) % positions.size]
}

fun changePos(waypoint: Pair<Int, Int>, dir: Char, angle: Int): Pair<Int, Int> {
    var move = ((if (dir == 'R') 1 else -1) * angle / 90) % 4
    if (move < 0) move += 4
    var wp = waypoint
    for (i in 1..move) {
        wp = wp.second to -wp.first
    }
    return wp
}