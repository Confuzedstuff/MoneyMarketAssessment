package com.shoprite.api

import com.shoprite.api.domain.WebHookCallbackUrl
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.boot.test.context.SpringBootTest
import java.net.MalformedURLException
import java.util.stream.Stream

@SpringBootTest
class ApiApplicationTests {
    @ParameterizedTest
    @MethodSource("validUrls")
    fun `given valid url then create valid callback url`(rawUrl: String) {
        WebHookCallbackUrl.create(rawUrl)
    }
    @ParameterizedTest
    @MethodSource("invalidUrls")
    fun `given invalid url then throw exception`(rawUrl: String) {
        assertThrows<MalformedURLException> {
            WebHookCallbackUrl.create(rawUrl)
        }
    }
    companion object {
        @JvmStatic
        private fun validUrls() = Stream.of(
            "https://www.test.org/callback",
            "https://www.test-org.org/another-callback"
        )
        @JvmStatic
        private fun invalidUrls() = Stream.of(
            "1234",
            "htt#ps://www.test-org.org/another-callback",
            "asdf",
        )
    }

}
