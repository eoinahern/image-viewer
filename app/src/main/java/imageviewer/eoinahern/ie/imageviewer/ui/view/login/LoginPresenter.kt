package imageviewer.eoinahern.ie.imageviewer.ui.view.login

import imageviewer.eoinahern.ie.imageviewer.data.model.UserCredentials
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseSubscriber
import imageviewer.eoinahern.ie.imageviewer.domain.login.LoginUserInteractor
import imageviewer.eoinahern.ie.imageviewer.tools.string.Validation
import imageviewer.eoinahern.ie.imageviewer.ui.base.BasePresenter
import javax.inject.Inject

@PerScreen
class LoginPresenter @Inject constructor(private val loginUserInteractor: LoginUserInteractor,
										 private val validation: Validation) : BasePresenter<LoginView>() {


	fun loginUser(userCredentials: UserCredentials) {

		loginUserInteractor.setUserCredentials(userCredentials)
				.execute(object : BaseSubscriber<Int>() {
					override fun onNext(count: Int) {

						if (count == 1) {
							getView()?.hideLoading()
							getView()?.goToSelection()
						} else {
							getView()?.onLoginFailed()
						}
					}

					override fun onError(e: Throwable) {
						getView()?.onLoginFailed()
					}

				})
	}

	fun validateEmail(emailStr: String) {
		getView()?.showEmailValidationError(validation.validateEmail(emailStr))
	}
}