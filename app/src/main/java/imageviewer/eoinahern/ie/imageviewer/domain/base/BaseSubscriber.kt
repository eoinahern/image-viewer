package imageviewer.eoinahern.ie.imageviewer.domain.base

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class BaseSubscriber<T> : Observer<T> {


	override fun onSubscribe(d: Disposable) {

	}

	override fun onComplete() {

	}

}