package imageviewer.eoinahern.ie.imageviewer.tools.views

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import imageviewer.eoinahern.ie.imageviewer.R


class LoadingView : FrameLayout {

	private lateinit var progressLayout: LinearLayout
	private lateinit var errorText: TextView
	private lateinit var loadingText: TextView

	constructor(context: Context) : super(context) {
		initialise()
	}

	constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
		initialise()
	}

	constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
		initialise()
	}


	private fun initialise() {
		val v = inflate(context, R.layout.loading_view_layout, this)
		progressLayout = v.findViewById(R.id.loading_view)
		errorText = v.findViewById(R.id.error_text)
		loadingText = v.findViewById(R.id.loading_message)
	}


	fun updateErrorMessage(@StringRes errorMessage: Int) {
		errorText.text = resources.getText(errorMessage)
	}

	fun setErrorMessageColor(@ColorRes colour: Int) {
		errorText.setTextColor(ContextCompat.getColor(context, colour))
	}

	fun hideLoadingMessage() {
		loadingText.visibility = View.GONE
	}

	fun hideLoading() {
		visibility = View.GONE
	}

	fun showError() {
		progressLayout.visibility = View.GONE
		errorText.visibility = View.VISIBLE
	}

	fun hideError() {
		errorText.visibility = View.GONE
	}

	fun showLoading() {
		visibility = View.VISIBLE
		progressLayout.visibility = View.VISIBLE
		errorText.visibility = View.GONE
	}
}