package pl.ideffix.day8

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day8/input.txt")

    for (line in lines) {
        if (line.split(' ')[0] == "acc") continue
        var acc = 0
        var currentLine = 0
        var visitedLines = mutableSetOf<Int>()
        while (true) {
            if (currentLine == lines.size) {
                println(acc)
                break
            }
            if (visitedLines.contains(currentLine) || currentLine < 0) {
                break
            } else {
                visitedLines.add(currentLine)
                var l = when(line == lines[currentLine]) {
                    true -> {
                        if (line.contains("nop")) {
                            line.replace("nop", "jmp")
                        } else {
                            line.replace("jmp", "nop")
                        }
                    }
                    false -> lines[currentLine]
                }
                val split = l.split(' ')
                when(split[0]) {
                    "acc" -> {
                        acc += split[1].toInt()
                        currentLine++
                    }
                    "jmp" -> currentLine += split[1].toInt()
                    "nop" -> currentLine++
                }
            }
        }
    }
}