package imageviewer.eoinahern.ie.imageviewer.domain.login

import imageviewer.eoinahern.ie.imageviewer.data.database.AppDataBase
import imageviewer.eoinahern.ie.imageviewer.data.database.dao.UserDao
import imageviewer.eoinahern.ie.imageviewer.data.model.UserCredentials
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class LoginUserInteractor @Inject constructor(database: AppDataBase) : BaseInteractor<Boolean>() {

	private lateinit var userCredentials: UserCredentials
	private var userDB: UserDao = database.getUserDao()


	fun setUserCredentials(userCredentials: UserCredentials): LoginUserInteractor {
		this.userCredentials = userCredentials
		return this
	}

	override fun buildObservable(): Observable<Boolean> {
		return Observable.fromCallable {
			true
		}
	}


}