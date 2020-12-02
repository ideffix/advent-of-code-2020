package pl.ideffix.day2

class PasswordPolicy(private val from: Int, private val to: Int, private val char: Char) {

    fun performCheck(password: String): Boolean {
        var count = 0
        for (char in password) {
            if (this.char == char) {
                count++;
            }
        }
        return count in from..to
    }

    fun performCheck2(password: String): Boolean {
        var count = 0
        if (password[from-1] == char) count++
        if (password[to-1] == char) count++
        return count == 1
    }

}