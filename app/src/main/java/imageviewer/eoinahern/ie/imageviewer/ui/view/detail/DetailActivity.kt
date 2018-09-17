package imageviewer.eoinahern.ie.imageviewer.ui.view.detail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import imageviewer.eoinahern.ie.imageviewer.R
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.data.model.getChannelUrl
import imageviewer.eoinahern.ie.imageviewer.tools.CHANNEL_EXTRA
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

	private lateinit var channel: Channel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)
		getChannelData()
		showUrl()
		setUpClose()
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

	companion object {

		fun getStartIntent(context: Context): Intent {
			return Intent(context, DetailActivity::class.java)
		}
	}
}
