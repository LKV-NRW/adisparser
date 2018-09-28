package de.lkv.nrw.adisparser.exceptions

class PropertyLineException(msg: String) : IllegalArgumentException(msg) {
    constructor() : this("")
}