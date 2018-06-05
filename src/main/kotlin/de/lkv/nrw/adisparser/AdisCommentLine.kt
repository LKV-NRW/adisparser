package de.lkv.nrw.adisparser

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
            throw IllegalArgumentException("Unknown comment type. This should not have happened.")
        }

        comment = line.substring(2).trim()
    }

    enum class CommentType {
        ERROR, NORMAL, UNKNOWN
    }
}