package pl.ideffix.day19

import pl.ideffix.utils.FileUtils

fun main() {
    val lines = FileUtils.readAllLines("src/main/kotlin/pl/ideffix/day19/input.txt")
    val rulesMap = mutableMapOf<String, String>()
    val words = mutableListOf<String>()
    var mode = 0
    for (line in lines) {
        if (line.isNullOrEmpty()) {
            mode = 1
            continue
        }
        when(mode) {
            0 -> {
                val split = line.split(':')
                rulesMap[split[0]] = split[1].trim()
            }
            1 -> words.add(line)
        }
    }
    var count = 0
    for (word in words) {
        if (isMatch(rulesMap, word, listOf(0))) count++
    }
    println(count)
}

fun createRegex(rulesMap: Map<String, String>, ruleNr: String): String {
    val rule = rulesMap[ruleNr]!!
    if (rule.contains("\"")) return rule.replace("\"", "")
    val split = rule.split('|')
    val l = mutableListOf<String>()
    for (ruleList in split) {
        var regexp = ""
        for (rl in ruleList.trim().split(" ")) {
            regexp += createRegex(rulesMap, rl)
        }
        l.add(regexp)
    }
    return "(${l.joinToString("|")})"
}

private fun isMatch(ruleMap: Map<String, String>, line: CharSequence, rules: List<Int>): Boolean {
    if (line.isEmpty()) {
        return rules.isEmpty()
    } else if (rules.isEmpty()) {
        return false
    }
    return ruleMap.getValue(rules[0].toString()).let { rule ->
        if (rule[1] in 'a'..'z') {
            if (line.startsWith(rule[1])) {
                isMatch(ruleMap, line.drop(1), rules.drop(1))
            } else false
        } else rule.split(" | ").any {
            isMatch(ruleMap, line, it.split(" ").map(String::toInt) + rules.drop(1))
        }
    }
}
