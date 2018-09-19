package imageviewer.eoinahern.ie.imageviewer.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import imageviewer.eoinahern.ie.imageviewer.data.database.dao.ChannelDao
import imageviewer.eoinahern.ie.imageviewer.data.database.dao.UserDao
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.data.model.UserCredentials

@Database(entities = [Channel::class, UserCredentials::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

	abstract fun getUserDao(): UserDao

	abstract fun getChannelDao(): ChannelDao
}