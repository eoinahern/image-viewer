package imageviewer.eoinahern.ie.imageviewer.ui.view.selection

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import javax.inject.Inject

@PerScreen
class SelectionAdapter @Inject constructor(): RecyclerView.Adapter<SelectionAdapter.ViewHolder>() {

	private val channels : MutableList<Channel> = mutableListOf()

	override fun onCreateViewHolder(vg: ViewGroup, position: Int): ViewHolder {
		return ViewHolder(View(vg.context))
	}

	override fun getItemCount(): Int =  channels.size

	override fun onBindViewHolder(vh: ViewHolder, position: Int) {

	}


	fun updataData(channelsList : List<Channel>) {

		if(channels.isNotEmpty())
			channels.clear()

		channels.addAll(channelsList)
		notifyItemRangeInserted(0, channelsList.size)
	}

	class ViewHolder(v : View): RecyclerView.ViewHolder(v) {

	}
}