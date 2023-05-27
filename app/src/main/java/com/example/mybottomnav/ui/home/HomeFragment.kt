package com.example.mybottomnav.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybottomnav.MainActivity
import com.example.mybottomnav.R
import com.example.mybottomnav.databinding.FragmentHomeBinding
import com.example.mybottomnav.dummy.adapter.ListTanamanAdapter
import com.example.mybottomnav.dummy.data.Tanaman
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.text.DecimalFormat

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvTanaman: RecyclerView
    private lateinit var tvTemperature: TextView
    private lateinit var tvRegion: TextView
    private lateinit var tvWeather: TextView
    private val list = ArrayList<Tanaman>()

    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
    private val locationPermissionRequestCode = 10
//    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val API_KEY = "e61e1a9320bdb4be62e48baf4ef114b5"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        (requireActivity() as MainActivity).supportActionBar!!.hide()

        rvTanaman = binding.rvPopularPlants
        rvTanaman.setHasFixedSize(true)
        list.addAll(getListTanaman())
        showRecyclerList()


        tvTemperature = binding.tvTemperature
        tvRegion = binding.tvRegion
        tvWeather = binding.tvTypeWeather




        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        fetchWeatherData(26.630580, -81.849468)
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        startLocationUpdates()
//        if (ContextCompat.checkSelfPermission(
//                requireContext(),
//                locationPermission
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            fusedLocationClient.lastLocation
//                .addOnSuccessListener { location: Location? ->
//                    location?.let {
//                        val latitude = location.latitude
//                        val longitude = location.longitude
//
//                        fetchWeatherData(latitude, longitude)
//                    }
//                }
//        } else {
//            reqLocationPermission()
//        }
    }


    private fun reqLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(locationPermission),
            locationPermissionRequestCode
        )
    }

//    @Deprecated("Deprecated in Java")
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        if (requestCode == locationPermissionRequestCode) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, request location updates
////                startLocationUpdates()
//            } else {
//                // Permission denied, handle accordingly
//            }
//        }
//    }

    private fun startLocationUpdates() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                locationPermission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        val latitude = location.latitude
                        val longitude = location.longitude

                        fetchWeatherData(latitude, longitude)
                    }
                }
        } else {
            reqLocationPermission()
        }
    }




    private fun fetchWeatherData(latitude: Double, longitude: Double) {
        GlobalScope.launch(Dispatchers.IO) {
            val apiUrl =
                "https://api.openweathermap.org/data/2.5/weather?lat=$latitude&lon=$longitude&appid=$API_KEY"
            var connection: HttpURLConnection? = null
            try {
                val url = URL(apiUrl)
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = connection.inputStream
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val response = StringBuilder()
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        response.append(line)
                    }
                    reader.close()
                    val jsonObject = JSONObject(response.toString())
                    withContext(Dispatchers.Main) {
                        val weatherDescription =
                            jsonObject.getJSONArray("weather").getJSONObject(0)
                                .getString("description")
                        val countryCode = jsonObject.getJSONObject("sys").getString("country")
                        val region = jsonObject.getString("name")
                        val kelvinTemp = jsonObject.getJSONObject("main").getDouble("temp")
                        val celsiusTemp = kelvinTemp - 273.0
                        val temperature: String = DecimalFormat("##.##").format(celsiusTemp)

                        val words = weatherDescription.split(" ")
                        val formattedDesc = words.joinToString("\n")
                        tvRegion.text = "$region, $countryCode"
                        tvTemperature.text = getString(R.string.temperature, temperature)
                        tvWeather.text = formattedDesc
                        weatherTheme(formattedDesc)

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun weatherTheme(weather: String) {
        if (weather.contains("clear")) {
            binding.constraintLayout.setBackgroundResource(R.drawable.gradient_clear_sky)
            binding.imgItemPhoto.setImageResource(R.drawable.icon_clear)
        } else if (weather.contains("clouds")) {
            binding.constraintLayout.setBackgroundResource(R.drawable.gradient_broken_clouds)
            binding.imgItemPhoto.setImageResource(R.drawable.icon_broken_cloud)
        } else if (weather.contains("thunderstorm") && weather.contains("shower")) {
            binding.constraintLayout.setBackgroundResource(R.drawable.gradient_heavy_rain)
            binding.imgItemPhoto.setImageResource(R.drawable.icon_heavy_rain)
        } else if (weather.contains("thunderstorm") ) {
            binding.constraintLayout.setBackgroundResource(R.drawable.gradient_thunderstorm)
            binding.imgItemPhoto.setImageResource(R.drawable.icon_thunderstorm)
        } else if (weather.contains("rain")) {
            binding.constraintLayout.setBackgroundResource(R.drawable.gradient_rain)
            binding.imgItemPhoto.setImageResource(R.drawable.icon_rain)
        } else if (weather.contains("mist")) {
            binding.constraintLayout.setBackgroundResource(R.drawable.gradient_mist)
            binding.imgItemPhoto.setImageResource(R.drawable.icon_mist)
        } else if (weather.contains("snow")) {
            binding.constraintLayout.setBackgroundResource(R.drawable.gradient_cold)
            binding.imgItemPhoto.setImageResource(R.drawable.icon_cold)
        }
    }


    @SuppressLint("Recycle")
    private fun getListTanaman(): ArrayList<Tanaman> {
        val dataName = resources.getStringArray(R.array.data_tanaman)
        val dataPercent = resources.getStringArray(R.array.data_persen)
        val listTanaman = ArrayList<Tanaman>()
        for (i in dataName.indices) {
            val tanam = Tanaman(dataName[i], dataPercent[i])
            listTanaman.add(tanam)
        }
        return listTanaman
    }

    private fun showRecyclerList() {
        rvTanaman.layoutManager = LinearLayoutManager(activity)
        val listTanamanAdapter = ListTanamanAdapter(list)
        rvTanaman.adapter = listTanamanAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}