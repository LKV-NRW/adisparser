package de.lkv.nrw.adisparser

import java.util.regex.Pattern

data class Item(val item: Int, val length: Int, val resolution: Int, var value: String?) {

    private val nullPattern: Pattern = Pattern.compile("^\\?+$")
    private val zeroPattern: Pattern = Pattern.compile("^\\s*-?")

    fun getFormattedValue(): String? {
        if (value == null || value!!.isBlank() || nullPattern.matcher(value).matches())
            return null

        if (resolution > 0 && value!!.trim().toBigIntegerOrNull() != null) {
            val len = value!!.trim().length
            val negativeValue = value!!.trim().startsWith("-")

            var trimmed = ""
            if (negativeValue)
                trimmed = "-"
            trimmed += zeroPattern.matcher(value).replaceFirst("0".repeat(value!!.length - len + if(negativeValue) 2 else 1))

            return trimmed.substring(0, trimmed.length - resolution).toInt().toString() + "." + trimmed.substring(trimmed.length - resolution)
        } else if (value!!.trim().toBigIntegerOrNull() != null) {
            return value!!.trim().toBigInteger().toString()
        }

        return value!!.trim()
    }
}