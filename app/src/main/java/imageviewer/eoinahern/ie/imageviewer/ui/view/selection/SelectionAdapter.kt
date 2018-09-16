package imageviewer.eoinahern.ie.imageviewer.ui.view.selection

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import imageviewer.eoinahern.ie.imageviewer.R
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import javax.inject.Inject

@PerScreen
class SelectionAdapter @Inject constructor(private val presenter: SelectionPresenter) : RecyclerView.Adapter<SelectionAdapter.ViewHolder>() {

	private val channels: MutableList<Channel> = mutableListOf()
	private lateinit var channelSelectCallback: ChannelSelectCallback

	override fun onCreateViewHolder(vg: ViewGroup, position: Int): ViewHolder {
		val v: View = LayoutInflater.from(vg.context).inflate(R.layout.single_channel_view, vg, false)
		return ViewHolder(v, channelSelectCallback)
	}

	override fun getItemCount(): Int = channels.size

	override fun onBindViewHolder(vh: ViewHolder, position: Int) {

		val chan = channels[position]

		vh.id.text = chan.id
		vh.name.text = chan.name
		vh.title.text = chan.title
		vh.image.setImageURI(chan.image)
	}


	fun updataData(channelsList: List<Channel>) {

		if (channels.isNotEmpty())
			channels.clear()

		channels.addAll(channelsList)
		notifyItemRangeInserted(0, channelsList.size)
	}

	fun goToDetail(position: Int) {
		presenter.goToChannelDetail(channels[position])
	}

	fun setCallback(channelSelectCallback: ChannelSelectCallback) {
		this.channelSelectCallback = channelSelectCallback
	}

	class ViewHolder(v: View, channelSelectCallback: ChannelSelectCallback) : RecyclerView.ViewHolder(v) {

		val image: SimpleDraweeView by lazy { v.findViewById<SimpleDraweeView>(R.id.image) }
		val id: TextView by lazy { v.findViewById<TextView>(R.id.idText) }
		val name: TextView by lazy { v.findViewById<TextView>(R.id.nameText) }
		val title: TextView by lazy { v.findViewById<TextView>(R.id.titleText) }

		init {
			itemView.setOnClickListener {
				channelSelectCallback.onChannelSelected(adapterPosition)
			}
		}
	}
}