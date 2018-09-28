package de.lkv.nrw.adisparser

import de.lkv.nrw.adisparser.exceptions.CommentLineException

class AdisCommentLine(line: String) : AdisLine(line, LineType.COMMENT) {

    val comment: String
    val type: CommentType

    init {
        if (category == LineCategory.CN)
            type = CommentType.NORMAL
        else if (category == LineCategory.CF)
            type = CommentType.ERROR
        else {
            type = CommentType.UNKNOWN
            throw CommentLineException("Unknown comment type. This should not have happened.")
        }

        comment = line.substring(2).trim()
    }

    enum class CommentType {
        ERROR, NORMAL, UNKNOWN
    }
}