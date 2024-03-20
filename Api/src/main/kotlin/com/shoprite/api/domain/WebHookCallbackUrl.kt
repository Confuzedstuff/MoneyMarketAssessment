package com.shoprite.api.domain
import java.net.URL

data class WebHookCallbackUrl(val url: URL) {
    companion object {
        fun create(rawUrl: String): WebHookCallbackUrl {
            //Note this url should be checked in a service against whitelisted domains
            val url = URL(rawUrl)
            requireNotNull(url) {
                "Invalid callback URL $rawUrl"
            }
            return WebHookCallbackUrl(url)
        }
    }
}