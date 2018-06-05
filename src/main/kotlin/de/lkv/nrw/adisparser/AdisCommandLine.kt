package de.lkv.nrw.adisparser

class AdisCommandLine(line: String) : AdisLine(line, LineType.COMMAND) {

    init {
        if (line.length != 2)
            throw IllegalArgumentException("Line length should be equal to 2.")
    }
}