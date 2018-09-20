package imageviewer.eoinahern.ie.imageviewer.tools.string

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class StringUtilityTest {

	lateinit var stringUtil: StringUtility
	private val testString = "eeeeeeeeeeeeeeee"
	private val expected = "eeeeeeeeeeeeee..."

	@Before
	fun setUp() {

		stringUtil = StringUtility()
	}

	@Test
	fun testStringLen() {
		var returned = stringUtil.ellipsizeString(testString)
		assertEquals(expected, returned)
		assertEquals(17, returned.length)
	}
}