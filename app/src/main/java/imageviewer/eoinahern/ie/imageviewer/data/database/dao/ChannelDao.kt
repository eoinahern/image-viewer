package imageviewer.eoinahern.ie.imageviewer.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import imageviewer.eoinahern.ie.imageviewer.data.model.Channel
import io.reactivex.Single

@Dao
interface ChannelDao {

	@Insert
	fun insertChannels(channels: List<Channel>)

	@Query("DELETE FROM Channel")
	fun deleteAll()

	@Query("SELECT * FROM Channel")
	fun getAll(): Single<List<Channel>>


	@Query("SELECT COUNT(*) FROM Channel")
	fun countRows(): Single<Int>
}