package pl.ideffix.utils

import java.io.File

object FileUtils {
    fun readAllLines(fileName: String): List<String>
            = File(fileName).readLines()
}