package imageviewer.eoinahern.ie.imageviewer.domain.selection

import imageviewer.eoinahern.ie.imageviewer.data.api.MyApi
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import imageviewer.eoinahern.ie.imageviewer.data.model.ChannelData
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`

class GetSelectionInteractorTest {

	@Mock
	lateinit var mockMyApi: MyApi


	@Mock
	lateinit var mockChannelData: ChannelData

	@Mock
	lateinit var mockChannelList: List<Channel>

	@Mock
	lateinit var mockChannel: Channel

	private lateinit var getSelectionInteractor: GetSelectionInteractor

	@Before
	fun setUp() {

		MockitoAnnotations.initMocks(this)
		getSelectionInteractor = GetSelectionInteractor(mockMyApi)
	}

	@Test
	fun testBuildObservable() {

		`when`(mockMyApi.getChannels()).thenReturn(Observable.just(mockChannelData))
		`when`(mockChannelData.channels).thenReturn(mockChannelList)
		`when`(mockChannelList[0]).thenReturn(mockChannel)
		`when`(mockChannel.id).thenReturn("1")

		val channelList = getSelectionInteractor.buildObservable().blockingFirst()

		Mockito.verify(mockMyApi).getChannels()
		Mockito.verify(mockChannelData).channels

		assertEquals(channelList[0].id, "1")
	}
}