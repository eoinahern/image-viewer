package imageviewer.eoinahern.ie.imageviewer.di.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import imageviewer.eoinahern.ie.imageviewer.data.api.MyApi
import imageviewer.eoinahern.ie.imageviewer.tools.constant.ApiEndPoint
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

	@Singleton
	@Provides
	fun getMoshi(): Moshi {
		return Moshi.Builder().build()
	}

	@Singleton
	@Provides
	fun getApiEndpoint(moshi: Moshi): MyApi {

		return Retrofit.Builder()
				.baseUrl(ApiEndPoint)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(MoshiConverterFactory.create(moshi))
				.build().create(MyApi::class.java)
	}
}