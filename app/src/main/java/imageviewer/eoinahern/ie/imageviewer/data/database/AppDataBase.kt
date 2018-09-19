package imageviewer.eoinahern.ie.imageviewer.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import imageviewer.eoinahern.ie.imageviewer.data.database.dao.ChannelDao
import imageviewer.eoinahern.ie.imageviewer.data.database.dao.UserDao
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.data.model.UserCredentials
import imageviewer.eoinahern.ie.imageviewer.tools.constant.DB_NAME
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Database(entities = [Channel::class, UserCredentials::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {


	abstract fun getUserDao(): UserDao

	abstract fun getChannelDao(): ChannelDao

	companion object {

		fun getInstance(context: Context): AppDataBase {
			var dbInstance = Room.databaseBuilder(context,
					AppDataBase::class.java, DB_NAME).fallbackToDestructiveMigration().build()
			dbInstance.prePopulateDB()
			return dbInstance
		}
	}

	private fun prePopulateDB() {

		getUserDao().countItems().map {

			if (it == 0) {
				beginTransaction()
				try {
					val userCredentials = UserCredentials("eoinpahern@yahoo.co.uk", "password")
					getUserDao().insertUser(userCredentials)
					setTransactionSuccessful()
				} finally {
					endTransaction()
				}
			}
		}.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSuccess {
					println("db populated")
				}
				.doOnError {
					it.printStackTrace()
				}.subscribe()

	}
}
