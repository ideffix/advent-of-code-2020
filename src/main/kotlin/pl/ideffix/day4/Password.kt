package pl.ideffix.day4

class Password() {
    var byr: String? = null
    var iyr: String? = null
    var eyr: String? = null
    var hgt: String? = null
    var hcl: String? = null
    var ecl: String? = null
    var pid: String? = null
    var cid: String? = null
    fun isValid(): Boolean =
            !byr.isNullOrBlank() &&
                    !iyr.isNullOrBlank() &&
                    !eyr.isNullOrBlank() &&
                    !hgt.isNullOrBlank() &&
                    !hcl.isNullOrBlank() &&
                    !ecl.isNullOrBlank() &&
                    !pid.isNullOrBlank()


}
