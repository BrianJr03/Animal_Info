package jr.brian.volley.model.remote

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLEncoder

class VolleyHelper(private val context: Context) {

    fun getHeadlines(callback: OperationalCallback) {
        val requestQueue: RequestQueue = Volley.newRequestQueue(context)
        val encodedKey =
            URLEncoder.encode(Constants.NEWS_API_KEY, "UTF-8")
        val url2 = Constants.NEWS_BASE_URL + encodedKey
        var response: Current
        val strRequest = object : StringRequest(url2, {
            val typeToken = object : TypeToken<Current>() {}
            val gson = Gson()
            Log.i("RESPONSE_SUCCESS", it.toString())
            response = gson.fromJson(it, typeToken.type)
            callback.onSuccess(response)
        }, { Log.i("RESPONSE_FAIL", it.toString()) }) {
        }
        requestQueue.add(strRequest)
    }
}