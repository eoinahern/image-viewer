package imageviewer.eoinahern.ie.imageviewer.data.api

import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.data.model.ChannelData
import io.reactivex.Observable
import retrofit2.http.GET


interface MyApi {

	@GET("allchannellist")
	fun getChannels() : Observable<ChannelData>

}