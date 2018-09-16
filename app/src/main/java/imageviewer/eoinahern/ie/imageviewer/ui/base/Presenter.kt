package imageviewer.eoinahern.ie.imageviewer.ui.base


interface Presenter<V : BaseView> {

	fun attachView(view : V)

	fun getView() : V?

	fun detachView()
}