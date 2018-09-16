package imageviewer.eoinahern.ie.imageviewer.ui.view.detail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import imageviewer.eoinahern.ie.imageviewer.R
import imageviewer.eoinahern.ie.imageviewer.ui.view.selection.SelectionActivity

class DetailActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)
	}

	companion object {

		fun getStartIntent(context: Context): Intent {
			return Intent(context, DetailActivity::class.java)
		}
	}
}
