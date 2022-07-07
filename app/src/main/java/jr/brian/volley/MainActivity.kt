package jr.brian.volley

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jr.brian.volley.Constants.BASE_URL
import jr.brian.volley.Constants.MULTI_ANIMAL_END_POINT
import jr.brian.volley.adapter.AnimalAdapter
import jr.brian.volley.data.AnimalsResponse
import jr.brian.volley.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMultiAnimals()
    }

    private fun getMultiAnimals() {
        val requestQueue = Volley.newRequestQueue(this)
        val request = StringRequest(
            Request.Method.GET,
            BASE_URL + MULTI_ANIMAL_END_POINT,
            { apiResponse: String ->
                val typeToken = object : TypeToken<AnimalsResponse>() {}
                val gson = Gson()
                try {
                    val animalResponse: AnimalsResponse = gson.fromJson(apiResponse, typeToken.type)
                    initMultiAnimalData(animalResponse)
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            { error -> error.printStackTrace() }
        )
        requestQueue.add(request)
    }

    @SuppressLint("SetTextI18n")
    private fun initMultiAnimalData(animalResponse: AnimalsResponse) {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this)
        val animalAdapter = AnimalAdapter(this, animalResponse)
        binding.recyclerView.adapter = animalAdapter
    }
}