package com.moa.domain.admin.club.controller

import com.moa.domain.admin.club.dto.request.AdminClubCreateRequest
import com.moa.domain.admin.club.service.AdminClubService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "관리자: 동아리")
@RestController
@RequestMapping("/admin/clubs")
class AdminClubController(
    private val adminClubService: AdminClubService
) {
    @Operation(summary = "동아리 생성")
    @PostMapping
    fun createClub(@Valid @RequestBody request: AdminClubCreateRequest) = adminClubService.createClub(request)

    @Operation(summary = "동아리 수정")
    @PatchMapping
    fun updateClub() {

    }
}