package com.moa.domain.user.controller

import com.moa.domain.user.service.UserMeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/me")
class UserMeController(
    private val userMeService: UserMeService
) {
    @GetMapping
    fun getMe() = userMeService.getMe()
}