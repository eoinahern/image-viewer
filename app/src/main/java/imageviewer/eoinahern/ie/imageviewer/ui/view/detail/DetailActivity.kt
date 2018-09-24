package imageviewer.eoinahern.ie.imageviewer.ui.view.detail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjection
import imageviewer.eoinahern.ie.imageviewer.R
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.data.model.getChannelUrl
import imageviewer.eoinahern.ie.imageviewer.tools.constant.CHANNEL_EXTRA
import imageviewer.eoinahern.ie.imageviewer.tools.web.ChannelWebViewClient
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

	@Inject
	lateinit var channelWebViewClient: ChannelWebViewClient

	private lateinit var channel: Channel

	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidInjection.inject(this)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)
		setUpClient()
		getChannelData()
		showUrl()
		setUpClose()
	}

	private fun setUpClient() {
		channelWebViewClient.setLoadingView(loading)
		webview.webViewClient = channelWebViewClient
	}

	private fun getChannelData() {
		channel = intent.getParcelableExtra(CHANNEL_EXTRA)
	}

	private fun showUrl() {
		webview.settings.javaScriptEnabled = true
		webview.loadUrl(channel.getChannelUrl())
		urlText.text = channel.getChannelUrl()
	}

	private fun setUpClose() {
		closeButton.setOnClickListener {
			finish()
		}
	}
}
