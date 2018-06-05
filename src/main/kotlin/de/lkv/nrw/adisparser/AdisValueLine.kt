package de.lkv.nrw.adisparser

class AdisValueLine(line: String, val defLine: AdisDefinitionLine) : AdisLine(line, LineType.VALUE) {

    val items: LinkedHashMap<Int, Item>
        get() = defLine.items

    init {
        try {
            val entity = Integer.valueOf(line.substring(2, 8))
            if (entity != defLine.entity)
                throw IllegalArgumentException("Line is not the same entity number as the given definition line: " + line.substring(2, 8))
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Line appears to contain non-numerical symbols at position 2 to 8. Only numerical characters can be at those positions: " + line.substring(2, 8))
        }

        if (line.length != defLine.getValueLength())
            throw IllegalArgumentException("Value line has an unexpected length.")

        var values = line.substring(8, line.length)
        for (item in items.values) {
            val itVal = values.substring(0, item.length)
            values = values.substring(item.length, values.length)
            item.value = itVal
        }
    }

}