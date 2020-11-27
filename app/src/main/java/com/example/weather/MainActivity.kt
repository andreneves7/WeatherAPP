package com.example.weather

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.AsyncTask
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.R.layout.activity_main
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    var CITY: String = ""
    var IconTempo: String = ""
    //val API: String ="1b5ce9dd86efb73dab71ebd469cce738"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        CITY = message.toString()



        weatherTask().execute()
    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */
//            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
//            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
//            findViewById<TextView>(R.id.errorText).visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?

            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=1b5ce9dd86efb73dab71ebd469cce738").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }
            return response
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt: Long = jsonObj.getLong("dt")
                val updatedAtText =
                    "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                        Date(updatedAt * 1000)
                    )
                val temp = main.getString("temp") + "°C"
                val tempMin = "Min Temp: " + main.getString("temp_min") + "°C"
                val tempMax = "Max Temp: " + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")
                val iconWeather = weather.getString("icon")
                IconTempo = iconWeather

                


                val iconUrl = "http://openweathermap.org/img/wn/04n@2x.png"

                val address = jsonObj.getString("name") + ", " + sys.getString("country")

                /* Populating extracted data into our views */
                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text = updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
                findViewById<TextView>(R.id.sunrise).text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
                findViewById<TextView>(R.id.sunset).text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
                findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.pressure).text = pressure
                findViewById<TextView>(R.id.humidity).text = humidity
                val imagem : ImageView = findViewById(R.id.icon)

                if (IconTempo == "01d" || IconTempo == "01n" ) {
                    imagem.setImageResource(R.drawable.clear_sky)
                }

                if (IconTempo == "02d" || IconTempo == "02n" ) {
                    imagem.setImageResource(R.drawable.few_clouds)
                }
                if (IconTempo == "03d" || IconTempo == "03n" ) {
                    imagem.setImageResource(R.drawable.scattered_clouds)
                }

                if (IconTempo == "04d" || IconTempo == "04n" ) {
                    imagem.setImageResource(R.drawable.broken_clouds)
                }

                if (IconTempo == "09d" || IconTempo == "09n" ) {
                    imagem.setImageResource(R.drawable.shower_rain)
                }

                if (IconTempo == "10d" || IconTempo == "10n" ) {
                    imagem.setImageResource(R.drawable.rain)
                }

                if (IconTempo == "11d" || IconTempo == "11n" ) {
                    imagem.setImageResource(R.drawable.thunderstorm)
                }

                if (IconTempo == "13d" || IconTempo == "13n" ) {
                    imagem.setImageResource(R.drawable.snow)
                }

                if (IconTempo == "50d" || IconTempo == "50n" ) {
                    imagem.setImageResource(R.drawable.mist)
                }

                /* Views populated, Hiding the loader, Showing the main design */
//                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
//                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE

            } catch (e: Exception) {
//                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
//                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }

        }
    }

//    inner class weatherIcon() : AsyncTask<String, Void, String>() {
//
//
//        override fun doInBackground(vararg params: String?): String? {
//            var resposta: String?
//            try {
//                resposta = URL("http://openweathermap.org/img/wn/$IconTempo@2x.png").toString()
//            } catch (e: Exception) {
//                resposta = null
//            }
//            return resposta
//        }
//
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//
//            try {
//                /* Extracting JSON returns from the API */
//                val jsonObj = JSONObject(result)
//
//
//
//
//                val imagem = findViewById<ImageView>(R.id.icon)
//
//
//
//            }catch (e: Exception) {
//
//            }
//        }
//    }


}