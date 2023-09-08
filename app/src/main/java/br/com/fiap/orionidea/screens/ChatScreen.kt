package br.com.fiap.orionidea.screens

import androidx.compose.foundation.layout.*
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
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import br.com.fiap.orionidea.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val client = OkHttpClient()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GPTChatScreen() {
    val scope = rememberCoroutineScope()
    var question by remember { mutableStateOf("") }
    var response by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.talk_to_orionbot),
            fontSize = 24.sp,
            color = Color(0xFFF186A4),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        if (isLoading) {
            Text(
                text = stringResource(id = R.string.searching),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        if (response.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.orionbot_message),
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                        fontSize = 16.sp
                    )
                    Text(
                        text = response, modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color(0xFF319131), shape = RoundedCornerShape(16.dp)
            ), contentAlignment = Alignment.Center, content = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.tip),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        })

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = question, onValueChange = {
                    question = it
                }, keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Send, keyboardType = KeyboardType.Text
                ), keyboardActions = KeyboardActions(onSend = {
                    scope.launch {
                        isLoading = true
                        getResponse(question) { result ->
                            response = result
                            question = ""
                            isLoading = false
                        }
                    }
                }), modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    scope.launch {
                        isLoading = true
                        getResponse(question) { result ->
                            response = result
                            question = ""
                            isLoading = false
                        }
                    }
                }, modifier = Modifier.height(IntrinsicSize.Max)
            ) {
                Text(text = stringResource(id = R.string.send))
            }
        }
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

    val request = Request.Builder().url(url).addHeader("Content-Type", "application/json")
        .addHeader("Authorization", "Bearer $apiKey")
        .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull())).build()

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
