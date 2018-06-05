package de.lkv.nrw.adisparser

import org.jetbrains.annotations.NotNull
import java.io.File
import java.nio.charset.Charset
import java.util.*

class AdisParser {

    companion object {
        private const val DEFAULT_CHARSET : String = "ISO-8859-15"
    }

    fun readFile(@NotNull file: File, charset: String = DEFAULT_CHARSET): LinkedList<AdisLine> {
        if (!file.exists())
            throw NoSuchFileException(file, null, "The given file does not exist")
        if (file.exists() && !file.isFile)
            throw NoSuchFileException(file, null, "The given file appears to be a directory")

        val list = LinkedList<AdisLine>()
        for (line in file.readLines(Charset.forName(charset))) {
            list.add(AdisLine.parse(line)!!)
        }

        return list
    }

    fun readList(@NotNull lines: LinkedList<String>): LinkedList<AdisLine> {
        val list = LinkedList<AdisLine>()
        for (line in lines) {
            list.add(AdisLine.parse(line)!!)
        }

        return list
    }

}