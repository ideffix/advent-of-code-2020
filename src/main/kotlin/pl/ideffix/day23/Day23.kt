package pl.ideffix.day23

import pl.ideffix.utils.FileUtils
import kotlin.math.max

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day23/input.txt")
    val firstNode = parseInput(lines)
    println(solveFirst(firstNode))
}

fun solveFirst(firstNode: Node): String {
    var current = firstNode
    for (i in 0 until 100) {
        current.move()
        current = current.next!!
    }
    var res = ""
    var started = false
    while (true) {
        if (current.label == 1) {
            started = true
            if (res.isNotEmpty()) break
            current = current.next!!
            continue
        }
        if (started) {
            res += current.label
        }
        current = current.next!!
    }
    return res
}

fun parseInput(lines: List<String>): Node {
    val labels = lines[0].map { it.toString().toInt() }
    val first = Node(labels[0])
    Node.minLabel = labels.minOrNull()!!
    Node.maxLabel = labels.maxOrNull()!!
    Node.otherNodes[first.label] = first
    var current = first
    for (i in 1 until labels.size) {
        current.next = Node(labels[i])
        current = current.next!!
        Node.otherNodes[current.label] = current
    }
    current.next = first
    return first
}

class Node(var label: Int) {
    companion object {
        var minLabel = Int.MIN_VALUE
        var maxLabel = Int.MAX_VALUE
        var otherNodes = mutableMapOf<Int, Node>()
    }

    var next: Node? = null

    fun move() {
        val dest = resolveDestination()
        val threeCupsStart = next
        next = next?.next?.next?.next
        val end = dest.next
        dest.next = threeCupsStart
        threeCupsStart?.next?.next?.next = end
    }

    private fun resolveDestination(): Node {
        var dest = label-1
        outer@for (i in 0 until otherNodes.size) {
            if (dest < minLabel) dest = maxLabel
            var current = this.next
            for (i in 1..3) {
                if (current?.label == dest) {
                    dest--
                    continue@outer
                }
                current = current!!.next
            }
            break
        }

        return otherNodes[dest]!!
    }
}