package imageviewer.eoinahern.ie.imageviewer.domain.splash

import imageviewer.eoinahern.ie.imageviewer.data.database.dao.ChannelDao
import imageviewer.eoinahern.ie.imageviewer.tools.constant.LOGGEDIN
import imageviewer.eoinahern.ie.imageviewer.tools.preferences.SharedPreferencesHelper
import imageviewer.eoinahern.ie.imageviewer.tools.time.DateTimeValidation
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CheckStateInteractorTest {

	@Mock
	lateinit var mockChannelDao: ChannelDao

	@Mock
	lateinit var mockDateTimeValid: DateTimeValidation

	@Mock
	lateinit var mockSharedPreferencesHelper: SharedPreferencesHelper

	lateinit var checkStateInteractor: CheckStateInteractor

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		checkStateInteractor = CheckStateInteractor(mockChannelDao, mockDateTimeValid, mockSharedPreferencesHelper)
	}


	@Test
	fun testBuildObservable() {

		`when`(mockDateTimeValid.checkSessionTimeout()).thenReturn(false)
		`when`(mockSharedPreferencesHelper.getBool(LOGGEDIN)).thenReturn(true)
		val state = checkStateInteractor.buildObservable().blockingFirst()

		Mockito.verify(mockDateTimeValid).checkSessionTimeout()
		Mockito.verify(mockSharedPreferencesHelper).getBool(LOGGEDIN)

		assertEquals(state, true)
	}

	@Test
	fun testBuildObservableTimedOut() {

		`when`(mockDateTimeValid.checkSessionTimeout()).thenReturn(true)
		`when`(mockSharedPreferencesHelper.getBool(LOGGEDIN)).thenReturn(true)

		val state = checkStateInteractor.buildObservable().blockingFirst()

		Mockito.verify(mockDateTimeValid).checkSessionTimeout()
		Mockito.verify(mockSharedPreferencesHelper).saveBool(LOGGEDIN, false)
		Mockito.verify(mockChannelDao).deleteAll()
		Mockito.verify(mockSharedPreferencesHelper).saveBool(LOGGEDIN, false)

		assertEquals(state, false)
	}
}