package de.lkv.nrw.adisparser

import de.lkv.nrw.adisparser.exceptions.CommandLineException

class AdisCommandLine(line: String) : AdisLine(line, LineType.COMMAND) {

    init {
        if (line.length != 2)
            throw CommandLineException("Line length should be equal to 2.")
    }
}