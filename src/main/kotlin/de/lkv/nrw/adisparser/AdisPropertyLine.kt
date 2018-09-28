package de.lkv.nrw.adisparser

import de.lkv.nrw.adisparser.exceptions.PropertyLineException
import de.lkv.nrw.adisparser.helper.Base36

class AdisPropertyLine(line: String) : AdisLine(line, LineType.PROPERTY) {

    var entity: Int = 0
        private set
    val items: LinkedHashMap<Int, Item> = linkedMapOf()

    init {
        try {
            entity = Integer.valueOf(line.substring(2, 8))
        } catch (e: NumberFormatException) {
            throw PropertyLineException("Line appears to contain non-numerical symbols at position 2 to 8. Only numerical characters can be at those positions: " + line.substring(2, 8))
        }

        var l = line.substring(8)
        var item: Item
        while (l.isNotEmpty()) {
            if (l.length < 12)
                throw PropertyLineException("Line appears to be to small. End of line is: ${l}")

            val i: Int
            try {
                i = Integer.valueOf(l.substring(0, 8))
            } catch (e: NumberFormatException) {
                throw PropertyLineException("Line appears to contain non-numerical symbols on an item position. Only numerical characters can declare items: " + l.substring(0, 8))
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

            val value = l.substring(11, 11 + len)

            item = Item(i, len, res, value)
            items[i] = item

            l = l.substring(11 + len)
        }
    }
}