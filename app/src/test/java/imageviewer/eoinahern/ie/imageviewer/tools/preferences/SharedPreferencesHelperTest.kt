package imageviewer.eoinahern.ie.imageviewer.tools.preferences

import android.content.SharedPreferences
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SharedPreferencesHelperTest {

	@Mock
	lateinit var sharedPrefs: SharedPreferences

	@Mock
	lateinit var sharedPrefsEdit: SharedPreferences.Editor

	private lateinit var sharedPrefsHelper: SharedPreferencesHelper

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		sharedPrefsHelper = SharedPreferencesHelper(sharedPrefs, sharedPrefsEdit)
	}

	@Test
	fun getString() {
		`when`(sharedPrefs.getString("wee", "")).thenReturn("wee")
		sharedPrefsHelper.getString("wee")
		verify(sharedPrefs).getString("wee", "")
	}

	@Test
	fun getBool() {
		`when`(sharedPrefs.getBoolean("wee", false)).thenReturn(true)
		sharedPrefsHelper.getBool("wee")
		verify(sharedPrefs).getBoolean("wee", false)
	}
}