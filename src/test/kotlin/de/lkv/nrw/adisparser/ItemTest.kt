package de.lkv.nrw.adisparser

import org.junit.Test

class ItemTest {

    @Test
    fun `item value length of string`() {
        val it = Item(1, 10, 0, " ".repeat(5) + "hallo")

        assert(it.getFormattedValue()!!.length == 5)
    }

    @Test
    fun `item compare formatted value with resolution`() {
        val it = Item(1, 10, 2, " ".repeat(5) + "55002")

        assert(it.getFormattedValue()!! == "550.02")
    }

    @Test
    fun `item negative numeric value no zero`() {
        val it = Item(1, 10, 0, " ".repeat(4) + "-55002")

        assert(it.getFormattedValue()!! == "-55002")
    }

    @Test
    fun `item negative numeric value`() {
        val it = Item(1, 10, 0, "-" + "0".repeat(4) + "55002")

        assert(it.getFormattedValue()!! == "-55002")
    }

    @Test
    fun `item negative numeric value with resolution no zero`() {
        val it = Item(1, 10, 3, " ".repeat(4) + "-55002")

        assert(it.getFormattedValue()!! == "-55.002")
    }

    @Test
    fun `item negative numeric value with resolution`() {
        val it = Item(1, 10, 3, "-" + "0".repeat(4) + "55002")

        assert(it.getFormattedValue()!! == "-55.002")
    }

    @Test
    fun `item empty with ?`() {
        val it = Item(1, 10, 3, "?".repeat(10))

        assert(it.getFormattedValue() == null)
    }

    @Test
    fun `item empty with spaces`() {
        val it = Item(1, 10, 3, " ".repeat(10))

        assert(it.getFormattedValue() == null)
    }

    @Test
    fun `item with partial ?`() {
        val it = Item(1, 10, 3, " ".repeat(10) + "?".repeat(5))

        assert(it.getFormattedValue() == "?".repeat(5))
    }
}