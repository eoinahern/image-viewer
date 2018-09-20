package imageviewer.eoinahern.ie.imageviewer.tools.preferences

import android.content.SharedPreferences
import javax.inject.Inject


open class SharedPreferencesHelper @Inject constructor(private val sharedPreferences: SharedPreferences,
												  private val sharedPrefsEdit: SharedPreferences.Editor) {

	fun saveString(key: String, value: String) {
		sharedPrefsEdit.putString(key, value).commit()
	}

	fun getString(key: String): String? {
		return sharedPreferences.getString(key, "")
	}

	fun saveBool(key: String, value: Boolean) {
		sharedPrefsEdit.putBoolean(key, value).commit()
	}

	fun getBool(key: String): Boolean {
		return sharedPreferences.getBoolean(key, false)
	}
}