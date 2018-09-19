package imageviewer.eoinahern.ie.imageviewer.domain.splash

import android.content.SharedPreferences
import imageviewer.eoinahern.ie.imageviewer.data.database.dao.ChannelDao
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseInteractor
import imageviewer.eoinahern.ie.imageviewer.tools.constant.LOGGEDIN
import imageviewer.eoinahern.ie.imageviewer.tools.constant.TIMEOUT_STR
import imageviewer.eoinahern.ie.imageviewer.tools.preferences.SharedPreferencesHelper
import imageviewer.eoinahern.ie.imageviewer.tools.time.DateTimeValidation
import io.reactivex.Observable
import javax.inject.Inject


@PerScreen
class CheckStateInteractor @Inject constructor(private val channelDao: ChannelDao,
											   private val dateTimeValidation: DateTimeValidation,
											   private val sharedPrefsHelper: SharedPreferencesHelper) : BaseInteractor<Boolean>() {

	override fun buildObservable(): Observable<Boolean> {

		return Observable.fromCallable {
			dateTimeValidation.checkSessionTimeout()
		}.map { isTimedOut ->
			if (isTimedOut) {
				channelDao.deleteAll()
				sharedPrefsHelper.saveBool(LOGGEDIN, false)
			}
			(!isTimedOut && sharedPrefsHelper.getBool(LOGGEDIN))
		}
	}
}