package imageviewer.eoinahern.ie.imageviewer.ui.view.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjection
import imageviewer.eoinahern.ie.imageviewer.R
import imageviewer.eoinahern.ie.imageviewer.data.model.UserCredentials
import imageviewer.eoinahern.ie.imageviewer.ui.view.selection.SelectionActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginView {

	@Inject
	lateinit var presenter: LoginPresenter

	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidInjection.inject(this)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)
		presenter.attachView(this)
		setLoadingView()
		setNextBtn()
	}

	override fun showLoading() {
		loading.showLoading()
		loading.hideError()
		disableLoginButton()
	}

	override fun hideLoading() {
		loading.hideLoading()
		enableLoginButton()
	}

	private fun disableLoginButton() {
		nextBtn.isEnabled = false
	}

	private fun enableLoginButton() {
		nextBtn.isEnabled = true

	}

	override fun onLoginFailed() {
		loading.showError()
		enableLoginButton()
	}

	private fun setLoadingView() {
		loading.setErrorMessageColor(R.color.warning_red)
		loading.hideLoadingMessage()
		loading.updateErrorMessage(R.string.login_failed)
	}

	private fun clearEditTexts() {
		passwordTxt.text.clear()
		emailTxt.text.clear()
	}

	private fun setNextBtn() {
		nextBtn.setOnClickListener {
			showLoading()
			presenter.loginUser(UserCredentials(emailTxt.text.toString(),
					passwordTxt.text.toString()))
		}
	}

	override fun goToSelection() {
		startActivity(SelectionActivity.getStartIntent(this))
		finish()
	}
}
