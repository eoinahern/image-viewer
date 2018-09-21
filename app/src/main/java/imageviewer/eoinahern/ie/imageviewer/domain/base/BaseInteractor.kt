package imageviewer.eoinahern.ie.imageviewer.domain.base

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


abstract class BaseInteractor<T> {

	var compDisposable: CompositeDisposable = CompositeDisposable()

	private lateinit var mainScheduler: Scheduler
	private lateinit var threadScheduler: Scheduler

	init {
		setMainScheduler()
		setThreadScheduler()
	}

	fun execute(obs: BaseSubscriber<T>) {

		buildObservable()
				.subscribeOn(threadScheduler)
				.observeOn(mainScheduler)
				.subscribe(obs)
	}

	fun setThreadScheduler(scheduler: Scheduler = Schedulers.io()) {
		threadScheduler = scheduler
	}

	fun setMainScheduler(scheduler: Scheduler = AndroidSchedulers.mainThread()) {
		mainScheduler = scheduler
	}

	abstract fun buildObservable(): Observable<T>

	fun clearDisposables() {
		compDisposable.clear()
	}
}