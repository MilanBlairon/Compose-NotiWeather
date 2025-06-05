package be.vives.notiweather

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Data classes voor het JSON-resultaat

data class WeatherResponse(
    val weather: List<WeatherDescription>,
    val main: MainWeather
)

data class WeatherDescription(
    val main: String,
    val description: String
)

data class MainWeather(
    val temp: Float
)

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse

    companion object {
        fun create(): WeatherApiService {
            return Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)
        }
    }
}

