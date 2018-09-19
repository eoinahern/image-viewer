package imageviewer.eoinahern.ie.imageviewer.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import imageviewer.eoinahern.ie.imageviewer.data.model.UserCredentials
import io.reactivex.Single

@Dao
interface UserDao {

	@Insert
	fun insertUser(userCredentials: UserCredentials)

	@Query("SELECT COUNT(*) FROM UserCredentials")
	fun countItems(): Single<Int>

	@Query("SELECT COUNT(*) FROM UserCredentials WHERE  email = :userEmail AND  password  = :userPass")
	fun checkUser(userEmail: String, userPass: String): Single<Int>
}