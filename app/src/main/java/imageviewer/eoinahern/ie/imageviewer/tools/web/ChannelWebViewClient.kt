package imageviewer.eoinahern.ie.imageviewer.tools.web

import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.tools.views.LoadingView
import javax.inject.Inject

@PerScreen
class ChannelWebViewClient @Inject constructor() : WebViewClient() {

	private lateinit var loadingView: LoadingView

	override fun onPageFinished(view: WebView?, url: String?) {
		super.onPageFinished(view, url)
		loadingView.hideLoading()
		view?.visibility = View.VISIBLE
	}

	override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
		super.onReceivedHttpError(view, request, errorResponse)
		loadingView.showError()
	}

	override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
		loadingView.hideLoading()
		view?.visibility = View.VISIBLE
		return super.shouldOverrideUrlLoading(view, request)
	}

	fun setLoadingView(loadingView: LoadingView) {
		this.loadingView = loadingView
	}
}