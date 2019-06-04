package com.irrational.request

data class CardRequest(
    var panFragment: String,
    var type: Int,
    var csc: Long
)