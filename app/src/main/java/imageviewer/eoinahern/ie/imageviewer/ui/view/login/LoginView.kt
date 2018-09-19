package imageviewer.eoinahern.ie.imageviewer.ui.view.login

import imageviewer.eoinahern.ie.imageviewer.ui.base.BaseView


interface LoginView : BaseView {

	fun showLoading()

	fun hideLoading()

	fun onLoginFailed()

	fun goToSelection()

	fun showEmailValidationError(valid: Boolean)

}