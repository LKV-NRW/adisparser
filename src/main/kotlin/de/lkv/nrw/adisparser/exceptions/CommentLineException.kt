package de.lkv.nrw.adisparser.exceptions

class CommentLineException(msg: String) : IllegalArgumentException(msg) {
    constructor() : this("")
}