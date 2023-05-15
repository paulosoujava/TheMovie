package com.paulo.mymovie.data.repositories.network.impls

object Helper {
    fun readFileResource(filename: String): String {
        return Helper::class.java.getResource(filename)?.readText() ?: ""
    }
}