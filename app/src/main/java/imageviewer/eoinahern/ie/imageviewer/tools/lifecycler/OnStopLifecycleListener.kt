package imageviewer.eoinahern.ie.imageviewer.tools.lifecycler

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import imageviewer.eoinahern.ie.imageviewer.tools.constant.TIMEOUT_STR
import imageviewer.eoinahern.ie.imageviewer.tools.preferences.SharedPreferencesHelper
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

class OnStopLifecycleListener @Inject constructor(private val sharedPrefsHelper: SharedPreferencesHelper) :
		LifecycleObserver {

	@OnLifecycleEvent(Lifecycle.Event.ON_STOP)
	fun onMoveToBackground() {
		sharedPrefsHelper.saveString(TIMEOUT_STR, LocalDateTime.now().plusSeconds(20)
				.toString())
	}

}