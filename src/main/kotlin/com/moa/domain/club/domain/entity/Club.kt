package com.moa.domain.club.domain.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "clubs")
class Club(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "description", nullable = false)
    val description: String,

    @Column(name = "image", nullable = false)
    val image: String,

    @OneToMany(mappedBy = "club", cascade = [CascadeType.ALL], orphanRemoval = true)
    val members: MutableList<ClubMember> = mutableListOf(),
)