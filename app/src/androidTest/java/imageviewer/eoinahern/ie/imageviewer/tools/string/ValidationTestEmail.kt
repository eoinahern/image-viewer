package imageviewer.eoinahern.ie.imageviewer.tools.string

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ValidationTestEmail {

	private lateinit var validation: Validation
	private val emailValid = "eoin@gmail.com"
	private val emailInValid = "whoppeeee"
	private val emailValidAgain = "hello@yahoo.com"

	@Before
	fun setUp() {
		validation = Validation()
	}

	@Test
	fun testEmails() {
		assertEquals(true, validation.validateEmail(emailValid))
		assertEquals(false, validation.validateEmail(emailInValid))
		assertEquals(true, validation.validateEmail(emailValidAgain))
	}
}