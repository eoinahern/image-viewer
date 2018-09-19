package imageviewer.eoinahern.ie.imageviewer.domain.splash

import imageviewer.eoinahern.ie.imageviewer.data.database.dao.ChannelDao
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseInteractor
import imageviewer.eoinahern.ie.imageviewer.tools.constant.TIMEOUT_STR
import imageviewer.eoinahern.ie.imageviewer.tools.preferences.SharedPreferencesHelper
import imageviewer.eoinahern.ie.imageviewer.tools.time.DateTimeValidation
import io.reactivex.Observable
import javax.inject.Inject


@PerScreen
class CheckTimeoutInteractor @Inject constructor(private val channelDao: ChannelDao,
												 private val sharedPrefsHelper: SharedPreferencesHelper,
												 private val dateTimeValidation: DateTimeValidation) : BaseInteractor<Boolean>() {

	override fun buildObservable(): Observable<Boolean> {

		return Observable.fromCallable {
			sharedPrefsHelper.getString(TIMEOUT_STR)
		}.map { timeoutStr ->
			dateTimeValidation.checkSessionTimeout(timeoutStr)
		}.map { isTimedOut ->
			if (isTimedOut) {
				channelDao.deleteAll()
			}
			isTimedOut
		}
	}
}