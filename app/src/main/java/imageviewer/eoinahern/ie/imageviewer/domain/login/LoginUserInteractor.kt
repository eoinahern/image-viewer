package imageviewer.eoinahern.ie.imageviewer.domain.login

import imageviewer.eoinahern.ie.imageviewer.data.database.dao.UserDao
import imageviewer.eoinahern.ie.imageviewer.data.model.UserCredentials
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseInteractor
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@PerScreen
class LoginUserInteractor @Inject constructor(private val userDao: UserDao) : BaseInteractor<Int>() {

	private lateinit var userCredentials: UserCredentials

	fun setUserCredentials(userCredentials: UserCredentials): LoginUserInteractor {
		this.userCredentials = userCredentials
		return this
	}

	override fun buildObservable(): Observable<Int> {

		return userDao.checkUser(userCredentials.email, userCredentials.password)
				.delay(2, TimeUnit.SECONDS)
				.toObservable()
	}
}