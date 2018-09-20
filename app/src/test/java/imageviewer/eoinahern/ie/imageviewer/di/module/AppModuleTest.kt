package imageviewer.eoinahern.ie.imageviewer.di.module

import android.content.Context
import android.content.SharedPreferences
import imageviewer.eoinahern.ie.imageviewer.App
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class AppModuleTest {

	@Mock
	lateinit var mockApp: App

	@Mock
	lateinit var mockAppContext: Context

	@Mock
	lateinit var mockSharedPrefs: SharedPreferences

	@Mock
	lateinit var mockSharedPrefsEdit: SharedPreferences.Editor

	lateinit var appModule: AppModule

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		appModule = AppModule(mockApp)
	}

	@Test
	fun getContext() {
		`when`(mockApp.applicationContext).thenReturn(mockAppContext)
		appModule.getContext()
		verify(mockApp).applicationContext
	}

	@Test
	fun getPrefsEdit() {
		`when`(mockSharedPrefs.edit()).thenReturn(mockSharedPrefsEdit)
		appModule.getPrefsEdit(mockSharedPrefs)
		verify(mockSharedPrefs).edit()
	}
}