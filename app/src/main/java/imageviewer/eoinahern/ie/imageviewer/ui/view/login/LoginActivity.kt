package imageviewer.eoinahern.ie.imageviewer.ui.view.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.AndroidInjection
import imageviewer.eoinahern.ie.imageviewer.R
import imageviewer.eoinahern.ie.imageviewer.data.model.UserCredentials
import imageviewer.eoinahern.ie.imageviewer.ui.view.selection.SelectionActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit
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
		setEmailChangeValid()
		setNextBtn()
		disableLoginButton()
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

	private fun setNextBtn() {
		nextBtn.setOnClickListener {
			showLoading()
			presenter.loginUser(UserCredentials(emailTxt.text.toString(),
					passwordTxt.text.toString()))
		}
	}

	private fun setEmailChangeValid() {
		RxTextView.textChanges(emailTxt)
				.debounce(1, TimeUnit.SECONDS)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe { presenter.validateEmail(it.toString()) }
	}

	override fun showEmailValidationError(valid: Boolean) {
		if (valid) {
			emailInputLayout.error = null
			enableLoginButton()
		} else {
			emailInputLayout.error = getString(R.string.invalid_email_error)
			disableLoginButton()
		}
	}

	override fun goToSelection() {
		startActivity(SelectionActivity.getStartIntent(this))
		finish()
	}
}
