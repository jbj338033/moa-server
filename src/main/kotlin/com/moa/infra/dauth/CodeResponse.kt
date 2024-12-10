package com.moa.infra.dauth

data class CodeResponse(
    private val data: CodeData
) {
    val code = data.location.split("=")[1].split("&")[0]
}

data class CodeData(
    val location: String
)