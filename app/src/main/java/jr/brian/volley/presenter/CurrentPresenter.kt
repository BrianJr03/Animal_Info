package jr.brian.volley.presenter

import jr.brian.volley.model.remote.Current
import jr.brian.volley.model.remote.OperationalCallback
import jr.brian.volley.model.remote.VolleyHelper

class CurrentPresenter(
    private val volleyHandler: VolleyHelper,
    private val view: CurrentMVP.CurrentView
) : CurrentMVP.CurrentPresenter {

    override fun getCategories() {
        view.onLoad(true)
        volleyHandler.getHeadlines(
            object : OperationalCallback {
                override fun onSuccess(message: Any) {
                    view.onLoad(false)
                    view.setResult(message as Current)
                }

                override fun onFailure(message: String) {
                    view.onLoad(false)
                    view.setResult(null)
                }
            })
    }
}