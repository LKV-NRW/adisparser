package de.lkv.nrw.adisparser

import org.junit.Test

class AdisLineTest {

    @Test
    fun `not an adis line`() {
        var l = AdisLine.parse("")
        assert(l !is AdisLine)

        l = AdisLine.parse(null.toString())
        assert(l !is AdisLine)

        l = AdisLine.parse("tn")
        assert(l !is AdisLine)

        try {
            l = AdisLine.parse("TNZN")
        } catch (ignore: Exception) {}
        assert(l !is AdisLine)

        try {
            l = AdisLine.parse("DN123AAA00123456121")
        } catch (ignore: Exception) {}
        assert(l !is AdisLine)

        try {
            l = AdisLine.parse("DN12345600123AAA121")
        } catch (ignore: Exception) {}
        assert(l !is AdisLine)

        try {
            l = AdisLine.parse("DN1234560012300012000123457")
        } catch (ignore: Exception) {}
        assert(l !is AdisLine)

        try {
            l = AdisLine.parse("PO123AAA00123456120abcabcabcabc")
        } catch (ignore: Exception) {}
        assert(l !is AdisLine)

        try {
            l = AdisLine.parse("PO12345600123AAA120abcabcabcabc")
        } catch (ignore: Exception) {}
        assert(l !is AdisLine)

        try {
            l = AdisLine.parse("PO12345600123000120abcabcabcabc00123457010")
        } catch (ignore: Exception) {}
        assert(l !is AdisLine)

        try {
            AdisLine.parse("DN1234560012345612000123457052")
            l = AdisLine.parse("VN123457abcabcabcabc  123")
        } catch (ignore: Exception) {}
        assert(l !is AdisLine)

        try {
            AdisLine.parse("DN1234560012345612000123457052")
            l = AdisLine.parse("VN123AAAabcabcabcabc  123")
        } catch (ignore: Exception) {}
        assert(l !is AdisLine)

        try {
            AdisLine.parse("DN1234560012345612000123457052")
            l = AdisLine.parse("VN123456abcabcabcabc  123000")
        } catch (ignore: Exception) {}
        assert(l !is AdisLine)
    }

    @Test
    fun `value but no definition`() {
        var l: AdisLine? = null
                try {
            l = AdisLine.parse("VN123456abcabcabcabc  123000")
        } catch (ignore: Exception) {}
        assert(l !is AdisLine)
    }

    @Test
    fun `long item`() {
        AdisLine.parse("DN87021000800100AA00080000415200800043020008200550200082013108000821017030008210399900082104602000900080150")
        val it = AdisLine.parse("VN870210Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy e276120721680012  ??20151026HRAöäüß@&%&?¤                                                                                         7 276001260605902")
        assert((it as AdisValueLine).items.get(800100)!!.length == 370)
    }
}