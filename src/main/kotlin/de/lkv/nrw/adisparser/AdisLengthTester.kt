package de.lkv.nrw.adisparser

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val parser = AdisParser()
    val zeilen = LinkedList<String>()

    try {
        File(args[0]).forEachLine { zeilen.add(it) }
        val returnedList = parser.readList(zeilen)
        for (line in returnedList) {
            if (line is AdisValueLine) {
                for (itemNumber in line.items.keys) {
                    val item = line.items[itemNumber]
//                    println("Item " + itemNumber + ", value: " + item.value)
                }
            }
        }
        println("All lines are adis compliant")
    } catch (e: Exception) {
        println(e.message)
        e.printStackTrace()
    }

}