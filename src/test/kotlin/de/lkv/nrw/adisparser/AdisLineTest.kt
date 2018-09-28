package de.lkv.nrw.adisparser

import de.lkv.nrw.adisparser.exceptions.*
import org.junit.Test

class AdisLineTest {

    @Test
    fun `not an adis line`() {
        try {
            AdisLine.parse("")
        } catch (ignore: LineCategoryUnknownException) {
        } catch (ignore: Exception) { assert(false) }

        try {
            AdisLine.parse(null.toString())
        } catch (ignore: LineCategoryUnknownException) {
        } catch (ignore: Exception) { assert(false) }

        try {
            AdisLine.parse("tn")
        } catch (ignore: LineCategoryUnknownException) {
        } catch (ignore: Exception) { assert(false) }

        try {
            AdisLine.parse("TNZN")
        } catch (ignore: CommandLineException) {
        } catch (ignore: Exception) { assert(false) }

        try {
            AdisLine.parse("DN123AAA00123456121")
        } catch (ignore: DefinitionLineException) {
        } catch (ignore: Exception) { assert(false) }

        try {
            AdisLine.parse("DN12345600123AAA121")
        } catch (ignore: DefinitionLineException) {
        } catch (ignore: Exception) { assert(false) }

        try {
            AdisLine.parse("DN1234560012300012000123457")
        } catch (ignore: DefinitionLineException) {
        } catch (ignore: Exception) { assert(false) }

        try {
            AdisLine.parse("PO123AAA00123456120abcabcabcabc")
        } catch (ignore: PropertyLineException) {
        } catch (ignore: Exception) { assert(false) }

        try {
            AdisLine.parse("PO12345600123AAA120abcabcabcabc")
        } catch (ignore: PropertyLineException) {
        } catch (ignore: Exception) { assert(false) }

        try {
            AdisLine.parse("PO12345600123000120abcabcabcabc00123457010")
        } catch (ignore: PropertyLineException) {
        } catch (ignore: Exception) { assert(false) }

        try {
            AdisLine.parse("DN1234560012345612000123457052")
            AdisLine.parse("VN123457abcabcabcabc  123")
        } catch (ignore: ValueLineException) {
        } catch (ignore: Exception) { assert(false) }

        try {
            AdisLine.parse("DN1234560012345612000123457052")
            AdisLine.parse("VN123AAAabcabcabcabc  123")
        } catch (ignore: ValueLineException) {
        } catch (ignore: Exception) { assert(false) }

        try {
            AdisLine.parse("DN1234560012345612000123457052")
            AdisLine.parse("VN123456abcabcabcabc  123000")
        } catch (ignore: ValueLineException) {
        } catch (ignore: Exception) { assert(false) }
    }

    @Test
    fun `value but no definition`() {
        try {
            AdisLine.parse("VN123456abcabcabcabc  123000")
        } catch (ignore: Exception) {
            assert(ignore !is DefinitionTypeMissingException)
        }
    }

    @Test
    fun `long item`() {
        AdisLine.parse("DN87021000800100AA00080000415200800043020008200550200082013108000821017030008210399900082104602000900080150")
        val it = AdisLine.parse("VN870210Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy e276120721680012  ??20151026HRAöäüß@&%&?¤                                                                                         7 276001260605902")
        assert((it as AdisValueLine).items.get(800100)!!.length == 370)
    }
}