package imageviewer.eoinahern.ie.imageviewer.tools.time

import imageviewer.eoinahern.ie.imageviewer.tools.constant.TIMEOUT_STR
import imageviewer.eoinahern.ie.imageviewer.tools.preferences.SharedPreferencesHelper
import org.threeten.bp.LocalDateTime
import javax.inject.Inject


class DateTimeValidation @Inject constructor(private val sharedPreferencesHelper: SharedPreferencesHelper) {

	fun checkSessionTimeout(): Boolean {

		val timeoutStr = sharedPreferencesHelper.getString(TIMEOUT_STR)

		if (timeoutStr.isNullOrEmpty())
			return true

		return LocalDateTime.now().isAfter(LocalDateTime
				.parse(timeoutStr))
	}
}