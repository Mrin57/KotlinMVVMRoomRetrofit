package com.electron.utility

import junit.framework.TestCase
import org.junit.Test

class CommonUtilKtTest : TestCase() {

    @Test
    fun testRoundToTwo() {
        val expected: String = "0.0"
        assertEquals(expected, roundToTwo(0.00f))
    }
}