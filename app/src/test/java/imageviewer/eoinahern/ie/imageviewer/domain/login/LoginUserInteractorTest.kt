package imageviewer.eoinahern.ie.imageviewer.domain.login

import imageviewer.eoinahern.ie.imageviewer.data.database.dao.UserDao
import imageviewer.eoinahern.ie.imageviewer.data.model.UserCredentials
import imageviewer.eoinahern.ie.imageviewer.tools.constant.LOGGEDIN
import imageviewer.eoinahern.ie.imageviewer.tools.preferences.SharedPreferencesHelper
import io.reactivex.Single
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class LoginUserInteractorTest {

	@Mock
	lateinit var mockUserDao: UserDao

	@Mock
	lateinit var mockSharedPrefs: SharedPreferencesHelper

	@Mock
	lateinit var mockCredentials: UserCredentials

	private val userEmail = "eoin"
	private val pass = "eoin"
	private lateinit var loginUserInteractor: LoginUserInteractor

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		loginUserInteractor = LoginUserInteractor(mockUserDao, mockSharedPrefs)
		`when`(mockCredentials.email).thenReturn(userEmail)
		`when`(mockCredentials.password).thenReturn(pass)
	}

	@Test
	fun testBuildObservable() {
		`when`(mockUserDao.checkUser(mockCredentials.email, mockCredentials.password))
				.thenReturn(Single.just(1))

		var numItems = loginUserInteractor.setUserCredentials(mockCredentials)
				.buildObservable().blockingFirst()

		verify(mockUserDao).checkUser(mockCredentials.email, mockCredentials.password)
		verify(mockSharedPrefs).saveBool(LOGGEDIN, true)
		assertEquals(numItems, 1)
	}
}