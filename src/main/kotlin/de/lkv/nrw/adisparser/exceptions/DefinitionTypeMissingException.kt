package de.lkv.nrw.adisparser.exceptions

class DefinitionTypeMissingException(msg: String) : IllegalArgumentException(msg) {
    constructor() : this("")
}