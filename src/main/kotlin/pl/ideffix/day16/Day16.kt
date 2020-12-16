package pl.ideffix.day16

import pl.ideffix.utils.FileUtils

data class Parsed(val rules: MutableList<Pair<String, List<Pair<Int, Int>>>>, val yourTicket: List<Int>, val nearbyTickets: MutableList<List<Int>>)

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day16/input.txt")
    val parsed = parseInput(lines)
    println(solveFirst(parsed))
    println(solveSecond(parsed))
}

fun solveFirst(parsed: Parsed): Int {
    var res = 0
    val wrongTickets = mutableListOf<List<Int>>()
    for (ticket in parsed.nearbyTickets) {
        ticket@for (num in ticket) {
            for (ruleWrapper in parsed.rules) {
                for (rule in ruleWrapper.second) {
                    if (num >= rule.first && num <= rule.second) continue@ticket
                }
            }
            res += num
            wrongTickets.add(ticket)
        }
    }
    parsed.nearbyTickets.removeAll(wrongTickets)
    return res
}

fun solveSecond(parsed: Parsed): Long {
    val tickets = parsed.nearbyTickets.toMutableList()
    tickets.add(parsed.yourTicket)
    val res = mutableListOf<Pair<String, Int>>()
    while(res.size < tickets[0].size) {
        for (i in tickets[0].indices) {
            val col = mutableListOf<Int>()
            for (j in tickets.indices) {
                col.add(tickets[j][i])
            }
            val a = parsed.rules.filter { rules ->
                col.stream().allMatch{ num ->  rules.second.any { rule -> num >= rule.first && num <= rule.second }}
            }
            if (a.size == 1) {
                res.add(a[0].first to i)
                parsed.rules.removeIf { it == a[0] }
            }
        }
    }
    return res.filter { it.first.startsWith("departure") }.fold(1, { acc, pair ->  acc * parsed.yourTicket[pair.second]})
}

fun parseInput(lines: List<String>): Parsed {
    var current = 0
    val yourTicket = mutableListOf<Int>()
    val nearbyTickets = mutableListOf<List<Int>>()
    val pairs = mutableListOf<Pair<String, List<Pair<Int, Int>>>>()
    for (line in lines) {
        if (line.isNullOrBlank()) {
            current++
            continue
        }
        when(current) {
            0 -> {
                pairs.add(parseRules(line))
            }
            1 -> {
                if (line == "your ticket:") continue
                line.split(',').forEach { yourTicket.add(it.toInt()) }
            }
            else -> {
                if (line == "nearby tickets:") continue
                nearbyTickets.add(line.split(',').map { it.toInt() })
            }
        }
    }
    return Parsed(pairs, yourTicket, nearbyTickets)
}

fun parseRules(line: String): Pair<String, List<Pair<Int, Int>>> {
    val split = line.split(':')
    val name = split[0]
    val rulesStr = split[1].split(" or ")
    val rules = mutableListOf<Pair<Int, Int>>()
    for (str in rulesStr) {
        val nums = str.split('-')
        rules.add(nums[0].trim().toInt() to nums[1].trim().toInt())
    }
    return name to rules
}
