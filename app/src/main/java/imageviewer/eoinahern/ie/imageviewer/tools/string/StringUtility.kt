package imageviewer.eoinahern.ie.imageviewer.tools.string

import imageviewer.eoinahern.ie.imageviewer.tools.constant.dots
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StringUtility @Inject constructor() {

	fun ellipsizeString(str: String, maxLength: Int = 15): String {

		return if (str.length >= maxLength) {
			str.substring(0, 14).plus(dots)
		} else {
			str
		}
	}
}