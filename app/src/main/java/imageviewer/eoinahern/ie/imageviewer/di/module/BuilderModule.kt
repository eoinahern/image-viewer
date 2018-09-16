package imageviewer.eoinahern.ie.imageviewer.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import imageviewer.eoinahern.ie.imageviewer.di.annotation.PerScreen
import imageviewer.eoinahern.ie.imageviewer.ui.view.selection.SelectionActivity

@Module
abstract class BuilderModule{

	 @PerScreen
	 @ContributesAndroidInjector
     abstract fun bindsSelectionActivity(): SelectionActivity

}