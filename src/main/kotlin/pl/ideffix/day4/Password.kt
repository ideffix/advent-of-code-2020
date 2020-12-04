package pl.ideffix.day4

val validColors = listOf(
        "amb", "blu", "brn", "gry", "grn", "hzl", "oth"
)

class Password {
    var byr: Int = -1
    var iyr: Int = -1
    var eyr: Int = -1
    var hgt: Height? = null
    var hcl: String? = null
    var ecl: String? = null
    var pid: String? = null
    var cid: String? = null
    fun isValid(): Boolean =
            byr in 1920..2002 &&
                    iyr in 2010..2020 &&
                    eyr in 2020..2030 &&
                    hgt != null && hgt!!.isValid() &&
                    !hcl.isNullOrBlank() && hcl!!.matches(Regex("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\$")) &&
                    ecl in validColors &&
                    !pid.isNullOrBlank() && pid!!.matches(Regex("\\(|\\)|\\d{9}"))


}

class Height(val value: Int, val type: String) {
    fun isValid(): Boolean {
        if (type == "cm") {
            return value in 150..193
        } else if (type == "in") {
            return value in 59..76
        }
        return false
    }
}
