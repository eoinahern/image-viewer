package imageviewer.eoinahern.ie.imageviewer.ui.view.login

import imageviewer.eoinahern.ie.imageviewer.data.model.UserCredentials
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseSubscriber
import imageviewer.eoinahern.ie.imageviewer.domain.login.LoginUserInteractor
import imageviewer.eoinahern.ie.imageviewer.tools.string.Validation
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class LoginPresenterTest {

	@Mock
	lateinit var loginUserInteractor: LoginUserInteractor

	@Mock
	lateinit var mockValidation: Validation

	@Mock
	lateinit var mockLoginView: LoginView

	@Mock
	lateinit var userCredentials: UserCredentials
	lateinit var loginPresenter: LoginPresenter
	private val testScheduler = TestScheduler()

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		loginPresenter = LoginPresenter(loginUserInteractor, mockValidation)
		loginPresenter.attachView(mockLoginView)

		`when`(loginUserInteractor.setUserCredentials(userCredentials))
				.thenReturn(loginUserInteractor)
	}


	//create helper class for this
	private fun <T> any(): T {
		Mockito.any<T>()
		return uninitialized()
	}

	private fun <T> uninitialized(): T = null as T

	@Test
	fun testLoginUser() {

		doAnswer { innvocation ->
			val subscriber = innvocation.arguments[0] as BaseSubscriber<Int>
			subscriber.onNext(1)
			subscriber.onComplete()
			null
		}.`when`(loginUserInteractor)
				.execute(any())

		loginUserInteractor.setMainScheduler(testScheduler)
		loginUserInteractor.setThreadScheduler(testScheduler)

		loginPresenter.loginUser(userCredentials)

		verify(loginUserInteractor).execute(any())
		verify(mockLoginView).hideLoading()
		verify(mockLoginView).goToSelection()
	}
}