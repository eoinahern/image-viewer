package imageviewer.eoinahern.ie.imageviewer

import android.app.Activity
import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import imageviewer.eoinahern.ie.imageviewer.di.component.DaggerAppComponent
import imageviewer.eoinahern.ie.imageviewer.di.module.AppModule
import imageviewer.eoinahern.ie.imageviewer.tools.lifecycler.OnStopLifecycleListener
import android.arch.lifecycle.ProcessLifecycleOwner
import com.jakewharton.threetenabp.AndroidThreeTen
import imageviewer.eoinahern.ie.imageviewer.di.component.AppComponent
import javax.inject.Inject

class App : Application(), HasActivityInjector {

	@Inject
	lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

	@Inject
	lateinit var lifecyclerListener: OnStopLifecycleListener

	override fun onCreate() {
		super.onCreate()

		Fresco.initialize(this)
		AndroidThreeTen.init(this)
		DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
		setupLifecyclerListener()
	}

	override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

	private fun setupLifecyclerListener() {
		ProcessLifecycleOwner.get().lifecycle
				.addObserver(lifecyclerListener)
	}
}