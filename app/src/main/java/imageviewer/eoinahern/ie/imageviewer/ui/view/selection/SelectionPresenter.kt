package imageviewer.eoinahern.ie.imageviewer.ui.view.selection

import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseSubscriber
import imageviewer.eoinahern.ie.imageviewer.domain.selection.GetSelectionInteractor
import imageviewer.eoinahern.ie.imageviewer.ui.base.BasePresenter
import javax.inject.Inject

@PerScreen
class SelectionPresenter @Inject constructor(private val getSelectionInteractor: GetSelectionInteractor) : BasePresenter<SelectionView>() {

	fun getChannelSelection() {

		getSelectionInteractor.execute(object : BaseSubscriber<List<Channel>>() {

			override fun onNext(channelList: List<Channel>) {
				getView()?.hideLoading()
				getView()?.onDataRetrieved(channelList)
			}

			override fun onError(e: Throwable) {
				e.printStackTrace()
				getView()?.onError()
			}
		})


	}

	override fun detachView() {
		super.detachView()
		getSelectionInteractor.clearDisposables()
	}


}