package imageviewer.eoinahern.ie.imageviewer

import android.app.Activity
import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import imageviewer.eoinahern.ie.imageviewer.di.component.DaggerAppComponent
import imageviewer.eoinahern.ie.imageviewer.di.module.AppModule
import javax.inject.Inject

class App : Application(), HasActivityInjector {

	@Inject
	lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

	override fun onCreate() {
		super.onCreate()

		Fresco.initialize(this)
		DaggerAppComponent.builder().build().inject(this)

				//.appModule(AppModule(this))
	}

	override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}