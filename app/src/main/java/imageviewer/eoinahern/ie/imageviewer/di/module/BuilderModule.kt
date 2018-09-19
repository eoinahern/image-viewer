package imageviewer.eoinahern.ie.imageviewer.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.ui.view.detail.DetailActivity
import imageviewer.eoinahern.ie.imageviewer.ui.view.login.LoginActivity
import imageviewer.eoinahern.ie.imageviewer.ui.view.selection.SelectionActivity
import imageviewer.eoinahern.ie.imageviewer.ui.view.splash.SplashActivity

@Module
abstract class BuilderModule {

	@PerScreen
	@ContributesAndroidInjector
	abstract fun bindsSelectionActivity(): SelectionActivity

	@PerScreen
	@ContributesAndroidInjector
	abstract fun bindsDetailActivity(): DetailActivity

	@PerScreen
	@ContributesAndroidInjector
	abstract fun bindsLoginActivity(): LoginActivity

	@PerScreen
	@ContributesAndroidInjector
	abstract fun bindsSplashActivity(): SplashActivity

}