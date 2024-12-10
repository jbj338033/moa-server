package com.moa.domain.club.repository

import com.moa.domain.club.domain.entity.Club
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ClubRepository: JpaRepository<Club ,UUID> {
}