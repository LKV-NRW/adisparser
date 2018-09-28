package de.lkv.nrw.adisparser.exceptions

class CommandLineException(msg: String) : IllegalArgumentException(msg) {
    constructor() : this("")
}