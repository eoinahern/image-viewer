package imageviewer.eoinahern.ie.imageviewer.tools.string

import android.util.Patterns


class Validation {
	fun validateEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}