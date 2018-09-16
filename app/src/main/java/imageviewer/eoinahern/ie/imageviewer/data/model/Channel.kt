package imageviewer.eoinahern.ie.imageviewer.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Channel(

		@Json(name = "id")
		val id: String,
		val fc2id: Long,
		val bid: String,
		val name: String,
		@Json(name = "title")
		val title: String,
		@Json(name= "image")
		val image: String)
