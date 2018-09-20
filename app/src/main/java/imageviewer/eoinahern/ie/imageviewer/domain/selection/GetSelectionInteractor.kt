package imageviewer.eoinahern.ie.imageviewer.domain.selection

import imageviewer.eoinahern.ie.imageviewer.data.api.MyApi
import imageviewer.eoinahern.ie.imageviewer.data.database.dao.ChannelDao
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject


@PerScreen
class GetSelectionInteractor @Inject constructor(private var myApi: MyApi,
												 private val channelDao: ChannelDao) : BaseInteractor<List<Channel>>() {

	override fun buildObservable(): Observable<List<Channel>> {
		return channelDao.countRows().toObservable().flatMap {
			if (it != 0) {
				channelDao.getAll().toObservable()
			} else {
				myApi.getChannels()
						.map { channelData -> channelData.channels }
						.map { chanList ->
							channelDao.insertChannels(chanList)
							chanList
						}
			}
		}
	}
}