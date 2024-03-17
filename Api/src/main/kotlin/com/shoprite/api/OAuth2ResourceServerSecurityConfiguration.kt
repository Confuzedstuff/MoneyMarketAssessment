package com.shoprite.api

import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig
import org.keycloak.util.JsonSerialization
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter
import org.springframework.security.web.SecurityFilterChain
import org.springframework.stereotype.Component



@Configuration
@EnableWebSecurity
class OAuth2ResourceServerSecurityConfiguration {
    @Value("\${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    var jwkSetUri: String? = null

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authorize ->
                authorize.requestMatchers("/**")
                    .permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer { configurer ->
                configurer.jwt(Customizer.withDefaults())
            }
            .addFilterAfter(createPolicyEnforcerFilter(), BearerTokenAuthenticationFilter::class.java)
        return http.build()
    }

    private fun createPolicyEnforcerFilter(): ServletPolicyEnforcerFilter {
        val config: PolicyEnforcerConfig = JsonSerialization.readValue(
            javaClass.getResourceAsStream("/policy-enforcer.json"),
            PolicyEnforcerConfig::class.java
        )
        return ServletPolicyEnforcerFilter { config }
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build()
    }

}