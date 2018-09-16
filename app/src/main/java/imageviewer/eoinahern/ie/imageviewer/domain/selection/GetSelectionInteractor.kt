package imageviewer.eoinahern.ie.imageviewer.domain.selection

import imageviewer.eoinahern.ie.imageviewer.data.api.MyApi
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject


@PerScreen
class GetSelectionInteractor @Inject constructor(private var myApi: MyApi) : BaseInteractor<List<Channel>>() {

	override fun buildObservable(): Observable<List<Channel>> {
		return myApi.getChannels().map { it.channels }
	}
}