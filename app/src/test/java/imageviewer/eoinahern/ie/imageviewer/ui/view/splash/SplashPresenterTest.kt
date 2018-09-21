package imageviewer.eoinahern.ie.imageviewer.ui.view.splash

import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseSubscriber
import imageviewer.eoinahern.ie.imageviewer.domain.splash.CheckStateInteractor
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class SplashPresenterTest {

	@Mock
	lateinit var mockCheckStateInteractor: CheckStateInteractor

	@Mock
	lateinit var splashView: SplashView

	private var testScheduler = TestScheduler()
	private lateinit var splashPresenter: SplashPresenter

	@Before
	fun setUp() {

		MockitoAnnotations.initMocks(this)
		splashPresenter = SplashPresenter(mockCheckStateInteractor)
		splashPresenter.attachView(splashView)
	}

	private fun <T> any(): T {
		Mockito.any<T>()
		return uninitialized()
	}

	private fun <T> uninitialized(): T = null as T

	@Test
	fun checkTimeoutExpired() {

		doAnswer { inn ->
			var args = inn.arguments[0] as? BaseSubscriber<Boolean>
			args?.onNext(true)
			args?.onComplete()
			null

		}.`when`(mockCheckStateInteractor)
				.execute(any())

		mockCheckStateInteractor.setThreadScheduler(testScheduler)
		mockCheckStateInteractor.setMainScheduler(testScheduler)

		//execute method under test
		splashPresenter.checkTimeoutExpired()

		//verify
		verify(mockCheckStateInteractor).execute(any())
		verify(splashView).goToSelectionScreen()
	}

}