package imageviewer.eoinahern.ie.imageviewer.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

	override fun onCreate(siState: Bundle?){
		super.onCreate(siState)

		if(getView() > -1) {

		}


		 inject()
	 }

	abstract fun inject()
	abstract fun getView() : Int

}