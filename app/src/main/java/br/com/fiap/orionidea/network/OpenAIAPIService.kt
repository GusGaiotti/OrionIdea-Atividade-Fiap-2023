import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OpenIAService {
    private val client = OkHttpClient()
    private val apiKey = "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"
    private val apiUrl = "https://api.openai.com/v1/engines/text-davinci-003/completions"

    suspend fun getResponse(question: String, callback: (String) -> Unit) {
        val requestBody = """
            {
                "prompt": "$question",
                "max_tokens": 500,
                "temperature": 0
            }
        """.trimIndent()

        val request = Request.Builder().url(apiUrl)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        try {
            val response = withContext(Dispatchers.IO) {
                client.newCall(request).execute()
            }

            if (response.isSuccessful) {
                val body = response.body?.string()
                if (body != null) {
                    val jsonObject = JSONObject(body)
                    if (jsonObject.has("choices")) {
                        val jsonArray: JSONArray = jsonObject.getJSONArray("choices")
                        if (jsonArray.length() > 0) {
                            val textResult = jsonArray.getJSONObject(0).getString("text")
                            withContext(Dispatchers.Main) {
                                callback(textResult)
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                callback("No response from the API.")
                            }
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            callback("Invalid response from the API.")
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        callback("Empty response from the API.")
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    callback("API failed with status code ${response.code}")
                }
            }
        } catch (e: IOException) {
            withContext(Dispatchers.Main) {
                callback("IO Exception occurred.")
            }
        }
    }
}
