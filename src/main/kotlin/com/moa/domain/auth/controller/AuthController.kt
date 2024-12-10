package com.moa.domain.auth.controller

import com.moa.domain.auth.dto.request.LoginRequest
import com.moa.domain.auth.service.AuthService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest) = authService.login(request)

    @GetMapping("/info")
    fun info() = authService.info()
}