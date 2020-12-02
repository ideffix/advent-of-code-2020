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

}