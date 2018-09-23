package imageviewer.eoinahern.ie.imageviewer.di.component

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import imageviewer.eoinahern.ie.imageviewer.App
import imageviewer.eoinahern.ie.imageviewer.di.module.AppModule
import imageviewer.eoinahern.ie.imageviewer.di.module.BuilderModule
import imageviewer.eoinahern.ie.imageviewer.di.module.NetworkModule
import imageviewer.eoinahern.ie.imageviewer.ui.view.splash.SplashPresenter

import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,  AppModule::class, BuilderModule::class, NetworkModule::class])
interface AppComponent {

	fun inject(app: App)
}