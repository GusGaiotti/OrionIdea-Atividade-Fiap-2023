package br.com.fiap.orionidea.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val client = OkHttpClient()

    @Composable
    fun GPTChatScreen(navController: NavHostController) {
        val scope = rememberCoroutineScope()
        var question by remember { mutableStateOf("") }
        var response by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            BasicTextField(
                value = question,
                onValueChange = {
                    question = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Send,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        scope.launch {
                            getResponse(question) { result ->
                                response = result
                            }
                        }
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch {
                        getResponse(question) { result ->
                            response = result
                        }
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Enviar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = response,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

private suspend fun getResponse(question: String, callback: (String) -> Unit) {
    val apiKey = "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"
    val url = "https://api.openai.com/v1/engines/text-davinci-003/completions"

    val requestBody = """
        {
            "prompt": "$question",
            "max_tokens": 500,
            "temperature": 0
        }
    """.trimIndent()

    val request = Request.Builder()
        .url(url)
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
                Log.v("data", body)
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
                Log.v("data", "empty")
                withContext(Dispatchers.Main) {
                    callback("Empty response from the API.")
                }
            }
        } else {
            Log.e("error", "API failed")
            withContext(Dispatchers.Main) {
                callback("API failed with status code ${response.code}")
            }
        }
    } catch (e: IOException) {
        Log.e("error", "IO Exception", e)
        withContext(Dispatchers.Main) {
            callback("IO Exception occurred.")
        }
    }
}
