package imageviewer.eoinahern.ie.imageviewer.ui.view.selection

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import imageviewer.eoinahern.ie.imageviewer.R
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_selection.*

class SelectionActivity : AppCompatActivity(), SelectionView {

	@Inject
	lateinit var presenter: SelectionPresenter

	@Inject
	lateinit var adapter: SelectionAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidInjection.inject(this)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_selection)
		presenter.attachView(this)
		setupRecycler()
		setAppBar()
		presenter.getChannelSelection()
	}


	private fun setupRecycler() {
		recycler.layoutManager = GridLayoutManager(this, 2)
		recycler.adapter = adapter
	}

	private fun setAppBar() {
		setSupportActionBar(toolbar)
		supportActionBar?.title = "Selection"
	}


	override fun showLoading() {

	}

	override fun hideLoading() {

	}

	override fun onError() {

	}

	override fun onDataRetrieved(channelList: List<Channel>) {
		adapter.updataData(channelList)
	}

	companion object {

		fun getStartIntent(context: Context): Intent {
			return Intent(context, SelectionActivity::class.java)
		}
	}


	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}
}
