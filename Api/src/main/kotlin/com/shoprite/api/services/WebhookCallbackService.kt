package com.shoprite.api.services

import com.shoprite.api.domain.WebHookCallbackUrl
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.stereotype.Service
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Service
class WebhookCallbackService {
    val jsonMediaType: MediaType = "application/json".toMediaType()
    val client: OkHttpClient = OkHttpClient()
    fun send(callbackUrl: WebHookCallbackUrl, message: Any) {
        //TODO check webhook whitelist here
        val json: String = Json.encodeToString(message)
        val body: RequestBody = json.toRequestBody(jsonMediaType)
        val request: Request = Request.Builder()
            .url(callbackUrl.url)
            .post(body)
            .build()
        client.newCall(request).execute()
    }
}

