package imageviewer.eoinahern.ie.imageviewer.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel

@Dao
interface ChannelDao {

	@Insert
	fun insertChannels(channels: List<Channel>)

}