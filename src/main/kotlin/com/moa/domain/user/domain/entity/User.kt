package com.moa.domain.user.domain.entity

import com.moa.domain.user.domain.enums.UserRole
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "dodam_id", nullable = false)
    val dodamId: String,

    @Column(name = "grade", nullable = false)
    var grade: Int,

    @Column(name = "room", nullable = false)
    var room: Int,

    @Column(name = "number", nullable = false)
    var number: Int,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "profileImage")
    var profileImage: String?,

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    var role: UserRole,

    @Column(name = "email", nullable = false)
    var email: String,
)