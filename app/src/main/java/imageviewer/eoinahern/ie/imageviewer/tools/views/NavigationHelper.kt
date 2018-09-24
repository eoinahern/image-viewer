package imageviewer.eoinahern.ie.imageviewer.tools.views

import android.app.Activity
import android.content.Context
import android.content.Intent


inline fun <reified T : Activity> Activity.navigateToActivity(context: Context) {
	startActivity(Intent(context, T::class.java))
}


inline fun <reified T : Activity> Activity.getStartIntent(context: Context): Intent = Intent(context, T::class.java)