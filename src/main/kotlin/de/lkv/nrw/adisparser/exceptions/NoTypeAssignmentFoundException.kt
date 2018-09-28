package de.lkv.nrw.adisparser.exceptions

class NoTypeAssignmentFoundException(msg: String) : Exception(msg) {
    constructor() : this("")
}