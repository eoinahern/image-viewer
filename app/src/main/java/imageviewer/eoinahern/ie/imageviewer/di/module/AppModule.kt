package imageviewer.eoinahern.ie.imageviewer.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import imageviewer.eoinahern.ie.imageviewer.App
import javax.inject.Singleton

@Module
class AppModule constructor(private val app: App) {

	@Singleton
	@Provides
	fun getContext(): Context = app.applicationContext

	@Singleton
	@Provides
	fun getPrefs(context : Context) : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)


}