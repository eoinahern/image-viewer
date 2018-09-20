package imageviewer.eoinahern.ie.imageviewer.domain.login

import imageviewer.eoinahern.ie.imageviewer.data.database.dao.UserDao
import imageviewer.eoinahern.ie.imageviewer.data.model.UserCredentials
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseInteractor
import imageviewer.eoinahern.ie.imageviewer.tools.constant.LOGGEDIN
import imageviewer.eoinahern.ie.imageviewer.tools.preferences.SharedPreferencesHelper
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@PerScreen
class LoginUserInteractor @Inject constructor(private val userDao: UserDao,
											  private val sharedPrefsHelper: SharedPreferencesHelper) : BaseInteractor<Int>() {

	private lateinit var userCredentials: UserCredentials

	fun setUserCredentials(userCredentials: UserCredentials): LoginUserInteractor {
		this.userCredentials = userCredentials
		return this
	}

	override fun buildObservable(): Observable<Int> {

		return userDao.checkUser(userCredentials.email, userCredentials.password)
				.map {
					if (it > 0)
						sharedPrefsHelper.saveBool(LOGGEDIN, true)
					it
				}.delay(2, TimeUnit.SECONDS)
				.toObservable()
	}
}