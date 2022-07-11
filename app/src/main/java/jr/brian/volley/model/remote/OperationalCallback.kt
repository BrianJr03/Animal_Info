package jr.brian.volley.model.remote

interface OperationalCallback {
    fun onSuccess(message: Any)
    fun onFailure(message: String)
}