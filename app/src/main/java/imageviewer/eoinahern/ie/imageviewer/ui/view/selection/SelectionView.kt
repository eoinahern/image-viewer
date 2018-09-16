package imageviewer.eoinahern.ie.imageviewer.ui.view.selection

import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.data.model.ChannelData
import imageviewer.eoinahern.ie.imageviewer.ui.base.BaseView


interface SelectionView : BaseView {


	fun showLoading()

	fun hideLoading()

	fun onError()

	fun onDataRetrieved(channelList: List<Channel>)

}