package imageviewer.eoinahern.ie.imageviewer.ui.view.splash

import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseSubscriber
import imageviewer.eoinahern.ie.imageviewer.domain.splash.CheckTimeoutInteractor
import imageviewer.eoinahern.ie.imageviewer.ui.base.BasePresenter
import javax.inject.Inject

@PerScreen
class SplashPresenter @Inject constructor(private val checkTimeoutInteractor: CheckTimeoutInteractor) : BasePresenter<SplashView>() {

	fun checkTimeoutExpired() {

		checkTimeoutInteractor.execute(object : BaseSubscriber<Boolean>() {
			override fun onNext(timedOut: Boolean) {

				if (timedOut) {
					getView()?.goToLoginScreen()
				} else {
					getView()?.goToSelectionScreen()
				}
			}

			override fun onError(e: Throwable) {
				getView()?.goToLoginScreen()
			}
		})

	}


}