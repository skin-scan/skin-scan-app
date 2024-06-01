package com.syafi.skinscan.data.remote

import com.google.gson.Gson
import com.syafi.skinscan.data.remote.response.ErrorResponse


class ErrorParser(
    private val gson: Gson
) {

    fun parse(errorString: String?): String? {
        val errorBody = gson.fromJson(errorString, ErrorResponse::class.java)
        return errorBody.message
    }
}