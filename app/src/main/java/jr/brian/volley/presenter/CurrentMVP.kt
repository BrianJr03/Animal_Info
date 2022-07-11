package jr.brian.volley.presenter

import jr.brian.volley.model.remote.Current

interface CurrentMVP {
    interface CurrentPresenter {
        fun getCategories()
    }

    interface CurrentView {
        fun setResult(current: Current?)
        fun onLoad(isLoading: Boolean)
    }
}