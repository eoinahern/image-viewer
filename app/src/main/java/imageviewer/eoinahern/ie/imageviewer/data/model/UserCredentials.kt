package imageviewer.eoinahern.ie.imageviewer.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class UserCredentials(
		@PrimaryKey
		val email: String,
		val password: String
)