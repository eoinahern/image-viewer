package imageviewer.eoinahern.ie.imageviewer.domain.base

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


abstract class BaseInteractor<T> {

	var compDisposable: CompositeDisposable = CompositeDisposable()

	fun execute(obs: Observer<T>) {

		buildObservable()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(obs)
	}

	abstract fun buildObservable(): Observable<T>

	fun clearDisposables() {
		compDisposable.clear()
	}
}