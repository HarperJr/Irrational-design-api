package com.irrational.utils

import io.ktor.http.HttpStatusCode

data class ApiException(val statusCode: HttpStatusCode, val errorMessage: String = "Internal error"): Exception(errorMessage)


