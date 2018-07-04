package de.lkv.nrw.adisparser

open class AdisLine internal constructor(line: String, type: LineType) {

    companion object {

        private var lastDefLine: AdisLine? = null

        /**
         * Returns a probability of the line being an adis line
         */
        fun isAdisLine(line: String) : Boolean {
            if (line.length < 2)
                return false

            val sub = line.substring(0, 2)
            return try {
                LineCategory.valueOf(sub)
                true
            } catch (ignore: IllegalArgumentException) {
                false
            }
        }

        /**
         * parses the given line accordingly to its type.
         */
        fun parse(line: String): AdisLine? {
            if(!isAdisLine(line))
                return null

            val cat = LineCategory.valueOf(line.substring(0, 2))

            if (cat.type == LineType.DEFINITION) {
                lastDefLine = AdisDefinitionLine(line)
                return lastDefLine
            } else if (cat.type == LineType.VALUE) {
                if (lastDefLine == null)
                    throw IllegalArgumentException("A definition line is required before a value line can be parsed.")
                return AdisValueLine(line, lastDefLine as AdisDefinitionLine)
            } else if (cat.type == LineType.PROPERTY) {
                return AdisPropertyLine(line)
            } else if (cat.type == LineType.COMMAND) {
                return AdisCommandLine(line)
            } else if (cat.type == LineType.COMMENT) {
                return AdisCommentLine(line)
            } else
                return null
        }
    }

    internal var category: LineCategory

    init {
        val sub = line.substring(0, 2)
        val headers = LineCategory.valuesOf(type)
        if (headers.none { it.name == sub })
            throw IllegalArgumentException("Line does not appear to be a valid adis line of type: ${type.name}")

        category = LineCategory.valueOf(sub)
    }

    internal enum class LineCategory(internal var type: LineType) {
        DH(LineType.DEFINITION),
        VH(LineType.VALUE),
        DN(LineType.DEFINITION),
        VN(LineType.VALUE),
        PO(LineType.PROPERTY),
        PN(LineType.PROPERTY),
        PR(LineType.PROPERTY),
        QO(LineType.PROPERTY),
        QN(LineType.PROPERTY),
        QR(LineType.PROPERTY),
        TN(LineType.COMMAND),
        EN(LineType.COMMAND),
        ZN(LineType.COMMAND),
        CN(LineType.COMMENT),
        CF(LineType.COMMENT);

        companion object {
            fun valuesOf(type: LineType): List<LineCategory> {
                return values().filter { it.type == type }
            }
        }

    }

    internal enum class LineType {
        DEFINITION, VALUE, COMMENT, PROPERTY, COMMAND
    }
}