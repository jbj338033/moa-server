package com.moa.infra.dauth

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class DAuthClient(private val dauthProperties: DAuthProperties) {
    private val client = WebClient.create("https://dauth.b1nd.com/api")

    fun getCode(id: String, password: String): String = client.post()
        .uri("/auth/login")
        .bodyValue(
            mapOf(
                "id" to id,
                "pw" to password,
                "clientId" to dauthProperties.clientId,
                "redirectUrl" to dauthProperties.redirectUrl,
            )
        )
        .retrieve()
        .bodyToMono<CodeResponse>()
        .block()?.code ?: throw RuntimeException("Failed to fetch code")

    fun getAccessToken(code: String): TokenResponse = client.post()
        .uri("/token")
        .bodyValue(
            mapOf(
                "code" to code,
                "client_id" to dauthProperties.clientId,
                "client_secret" to dauthProperties.clientSecret,
            )
        )
        .retrieve()
        .bodyToMono<TokenResponse>()
        .block() ?: throw RuntimeException("Failed to fetch access token")

    fun getUserInfo(accessToken: String): UserInfoData = WebClient.create("https://opendodam.b1nd.com/api")
        .get()
        .uri("/user")
        .headers { it.setBearerAuth(accessToken) }
        .retrieve()
        .bodyToMono<UserInfoResponse>()
        .block()?.data ?: throw RuntimeException("Failed to fetch user info")
}