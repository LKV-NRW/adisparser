package de.lkv.nrw.adisparser

import de.lkv.nrw.adisparser.exceptions.DefinitionLineException
import de.lkv.nrw.adisparser.helper.Base36

class AdisDefinitionLine(line: String) : AdisLine(line, LineType.DEFINITION) {

    var entity: Int = 0
        private set
    val items: LinkedHashMap<Int, Item> = linkedMapOf()

    init {
        try {
            entity = Integer.valueOf(line.substring(2, 8))
        } catch (e: NumberFormatException) {
            throw DefinitionLineException("Line appears to contain non-numerical symbols at position 2 to 8. Only numerical characters can be at those positions: " + line.substring(2, 8))
        }

        var l = line.substring(8, line.length)
        var item: Item
        while (l.isNotEmpty()) {
            if (l.length < 11)
                throw DefinitionLineException("Line appears to be to small. End of line is: ${l}")

            val i: Int
            try {
                i = Integer.valueOf(l.substring(0, 8))
            } catch (e: NumberFormatException) {
                throw DefinitionLineException("Line appears to contain non-numerical symbols on an item position. Only numerical characters can declare items: " + l.substring(0, 8))
            }

            val lenStr = l.substring(8, 10)
            var len: Int
            if (lenStr.toIntOrNull() != null)
                len = Integer.valueOf(lenStr)
            else
                len = Base36.base36ToBase10(lenStr).toInt()

            val resStr = l.substring(10, 11)
            var res: Int
            if (resStr.toIntOrNull() != null)
                res = Integer.valueOf(resStr)
            else
                res = Base36.base36ToBase10(lenStr).toInt()

            item = Item(i, len, res, null)
            items[i] = item

            l = l.substring(11, l.length)
        }
    }

    fun getValueLength(): Int {
        var i = 8
        for (l in items.values) {
            i += l.length
        }
        return i
    }
}
