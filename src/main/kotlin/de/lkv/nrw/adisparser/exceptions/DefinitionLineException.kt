package de.lkv.nrw.adisparser.exceptions

class DefinitionLineException(msg: String) : IllegalArgumentException(msg) {
    constructor() : this("")
}