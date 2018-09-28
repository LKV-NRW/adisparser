package de.lkv.nrw.adisparser.exceptions

class LineCategoryUnknownException(msg: String) : IllegalArgumentException(msg) {
    constructor() : this("")
}