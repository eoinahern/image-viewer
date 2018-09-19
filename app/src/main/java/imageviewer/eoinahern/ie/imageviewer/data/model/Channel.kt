package imageviewer.eoinahern.ie.imageviewer.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import imageviewer.eoinahern.ie.imageviewer.tools.constant.ChannelEndPoint
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
@Entity
@JsonClass(generateAdapter = true)
data class Channel(

		@Json(name = "id")
		@PrimaryKey
		val id: String,
		val fc2id: Long,
		val bid: String,
		val name: String,
		@Json(name = "title")
		val title: String,
		@Json(name = "image")
		val image: String) : PaperParcelable {

	companion object {
		@JvmField
		val CREATOR = PaperParcelChannel.CREATOR
	}

}


fun Channel.getChannelUrl() = ChannelEndPoint.plus(id)

