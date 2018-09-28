package de.lkv.nrw.adisparser.exceptions

class ValueLineException(msg: String) : IllegalArgumentException(msg) {
    constructor() : this("")
}