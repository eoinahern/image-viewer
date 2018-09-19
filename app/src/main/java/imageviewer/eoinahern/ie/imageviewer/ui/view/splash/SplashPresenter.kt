package imageviewer.eoinahern.ie.imageviewer.ui.view.splash

import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseSubscriber
import imageviewer.eoinahern.ie.imageviewer.domain.splash.CheckStateInteractor
import imageviewer.eoinahern.ie.imageviewer.ui.base.BasePresenter
import javax.inject.Inject

@PerScreen
class SplashPresenter @Inject constructor(private val checkStateInteractor: CheckStateInteractor) : BasePresenter<SplashView>() {

	fun checkTimeoutExpired() {

		checkStateInteractor.execute(object : BaseSubscriber<Boolean>() {
			override fun onNext(canProceed: Boolean) {

				if (canProceed) {
					getView()?.goToSelectionScreen()
				} else {
					getView()?.goToLoginScreen()
				}
			}

			override fun onError(e: Throwable) {
				getView()?.goToLoginScreen()
			}
		})
	}
}