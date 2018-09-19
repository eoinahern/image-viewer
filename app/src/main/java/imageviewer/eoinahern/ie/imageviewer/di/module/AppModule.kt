package imageviewer.eoinahern.ie.imageviewer.di.module

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import imageviewer.eoinahern.ie.imageviewer.App
import imageviewer.eoinahern.ie.imageviewer.data.database.AppDataBase
import javax.inject.Singleton

@Module
class AppModule constructor(private val app: App) {

	@Singleton
	@Provides
	fun getContext(): Context = app.applicationContext

	@Singleton
	@Provides
	fun getPrefs(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

	@Singleton
	@Provides
	fun getDatabase(context: Context): AppDataBase = Room.databaseBuilder(context,
			AppDataBase::class.java, "database")
			.fallbackToDestructiveMigration()
			.build()
}