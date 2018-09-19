package imageviewer.eoinahern.ie.imageviewer.domain.repository

import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import io.reactivex.Observable

interface ChannelSelectionRepository {

	fun getChannelList(): Observable<List<Channel>>
}