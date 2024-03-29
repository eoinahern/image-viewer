package imageviewer.eoinahern.ie.imageviewer.ui.view.selection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import imageviewer.eoinahern.ie.imageviewer.R
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.tools.constant.CHANNEL_EXTRA
import imageviewer.eoinahern.ie.imageviewer.tools.views.getStartIntent
import imageviewer.eoinahern.ie.imageviewer.ui.view.detail.DetailActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_selection.*

class SelectionActivity : AppCompatActivity(), SelectionView, ChannelSelectCallback {

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
		recycler.layoutManager = LinearLayoutManager(this)
		recycler.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
		adapter.setCallback(this)
		recycler.adapter = adapter
	}

	private fun setAppBar() {
		setSupportActionBar(toolbar)
		supportActionBar?.title = getString(R.string.selection_title)
	}

	override fun showLoading() {
		loading.showLoading()
	}

	override fun hideLoading() {
		loading.hideLoading()
	}

	override fun onError() {
		loading.showError()
	}

	override fun onDataRetrieved(channelList: List<Channel>) {
		adapter.updataData(channelList)
	}

	override fun onChannelSelected(position: Int) {
		adapter.goToDetail(position)
	}

	override fun navigateToDetail(channel: Channel) {
		val intent = getStartIntent<DetailActivity>(this)
		intent.putExtra(CHANNEL_EXTRA, channel)
		startActivity(intent)
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}
}
