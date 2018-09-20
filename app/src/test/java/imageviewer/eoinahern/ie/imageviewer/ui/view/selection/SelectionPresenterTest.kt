package imageviewer.eoinahern.ie.imageviewer.ui.view.selection

import imageviewer.eoinahern.ie.imageviewer.data.api.MyApi
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.domain.base.BaseSubscriber
import imageviewer.eoinahern.ie.imageviewer.domain.selection.GetSelectionInteractor
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import org.junit.Assert
import org.junit.Before


import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*


import org.mockito.MockitoAnnotations


class SelectionPresenterTest {


	@Mock
	lateinit var mockGetSelectionInteractor: GetSelectionInteractor

	@Mock
	lateinit var mockApi : MyApi

	@Mock
	lateinit var mockSelectionView: SelectionView

	@Mock
	lateinit var mockList: List<Channel>

	@Mock
	lateinit var mockObs: Observable<List<Channel>>


	lateinit var selectionPresenter: SelectionPresenter

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)

		selectionPresenter = SelectionPresenter(mockGetSelectionInteractor)
		selectionPresenter.attachView(mockSelectionView)
	}

	@Test
	public fun testGetChannelSelection() {

		/*`when`(mockGetSelectionInteractor.execute())
		`when`(mockGetSelectionInteractor.buildObservable()).thenReturn(mockObs)
		`when`(mockObs.subscribeOn(any(Scheduler::class.java))).thenReturn(mockObs)
		`when`(mockObs.observeOn(any(Scheduler::class.java))).thenReturn(mockObs)

		selectionPresenter.getChannelSelection()


		verify(mockGetSelectionInteractor).buildObservable()
		//verify(mockObs).observeOn(any(Scheduler::class.java))
		//verify(mockObs).subscribeOn(any(Scheduler::class.java))*/

	}
}