package imageviewer.eoinahern.ie.imageviewer.ui.view.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import dagger.android.AndroidInjection
import imageviewer.eoinahern.ie.imageviewer.R
import imageviewer.eoinahern.ie.imageviewer.ui.view.login.LoginActivity
import imageviewer.eoinahern.ie.imageviewer.ui.view.selection.SelectionActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashView {

	@Inject
	lateinit var presenter: SplashPresenter

	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidInjection.inject(this)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)
		presenter.attachView(this)
		startAnimation()
	}

	private fun startAnimation() {
		val startAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.alpha_animation)
		imview.startAnimation(startAnim)

		startAnim.setAnimationListener(object : Animation.AnimationListener {
			override fun onAnimationRepeat(animation: Animation?) {}
			override fun onAnimationEnd(animation: Animation?) {
				presenter.checkTimeoutExpired()
			}

			override fun onAnimationStart(animation: Animation?) {}
		})
	}

	override fun goToLoginScreen() {
		startActivity(LoginActivity.getStartIntent(this))
		finish()
	}

	override fun goToSelectionScreen() {
		startActivity(SelectionActivity.getStartIntent(this))
		finish()
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}


}
