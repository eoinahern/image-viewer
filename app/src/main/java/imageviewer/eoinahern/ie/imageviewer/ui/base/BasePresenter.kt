package imageviewer.eoinahern.ie.imageviewer.ui.base


open class BasePresenter<V : BaseView> : Presenter<V> {

	private var view: V? = null

	override fun attachView(view: V) {
		this.view = view
	}

	override fun getView(): V? = view


	override fun detachView() {
		this.view = null
	}
}