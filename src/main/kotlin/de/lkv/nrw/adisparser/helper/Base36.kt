package de.lkv.nrw.adisparser.helper

internal class Base36 {
    companion object {
        fun base10ToBase36(toNum: Int): String {
            return java.lang.Long.toString(toNum.toLong(), 36)
        }

        fun base36ToBase10(str: String): Long {
            if (str.isEmpty())
                throw NumberFormatException("Argument can not be null or empty")
            return java.lang.Long.valueOf(str, 36)
        }
    }
}