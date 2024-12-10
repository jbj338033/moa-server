package com.moa.domain.club.controller

import com.moa.domain.club.dto.request.ClubApplyRequest
import com.moa.domain.club.service.ClubService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/clubs")
class ClubController(
    private val clubService: ClubService
) {
    @Operation(summary = "동아리 목록 조회")
    @GetMapping
    fun getClubs(@PageableDefault pageable: Pageable) = clubService.getClubs(pageable)

    @Operation(summary = "동아리 상세 조회")
    @GetMapping("/{clubId}")
    fun getClub(@PathVariable clubId: UUID) = clubService.getClub(clubId)

    @Operation(summary = "동아리 지원")
    @PostMapping("/{clubId}/apply")
    fun applyClub(@PathVariable clubId: UUID, @RequestBody request: ClubApplyRequest) = clubService.applyClub(clubId, request)

    @Operation(summary = "동아리 지원 취소")
    @PostMapping("/{clubId}/cancel")
    fun cancelApply(@PathVariable clubId: UUID) = clubService.cancelApply(clubId)
}