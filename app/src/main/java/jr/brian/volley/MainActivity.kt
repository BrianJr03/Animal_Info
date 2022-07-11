package jr.brian.volley

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import jr.brian.volley.view.adapter.NewsAdapter
import jr.brian.volley.model.remote.Current
import jr.brian.volley.databinding.ActivityMainBinding
import jr.brian.volley.model.remote.VolleyHelper
import jr.brian.volley.presenter.CurrentMVP
import jr.brian.volley.presenter.CurrentPresenter

class MainActivity : AppCompatActivity(), CurrentMVP.CurrentView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: CurrentPresenter
    private lateinit var currentList: ArrayList<Current>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        currentList = ArrayList()
        presenter = CurrentPresenter(VolleyHelper(this), this)
        presenter.getCategories()
    }

    @SuppressLint("SetTextI18n")
    private fun setAdapter(current: Current) {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this)
        val newsAdapter = NewsAdapter(this, current.news)
        binding.recyclerView.adapter = newsAdapter
    }

    override fun setResult(current: Current?) {
        if (current != null) {
            setAdapter(current)
        } else {
            setAdapter(Current(ArrayList(), ""))
        }
    }

    override fun onLoad(isLoading: Boolean) {
    }
}