package be.school.quizzapplication.utils

import okhttp3.Cookie
import okhttp3.HttpUrl

class AppCookieJar : okhttp3.CookieJar {
    private val cookieStore: MutableMap<HttpUrl, MutableList<Cookie>> = mutableMapOf()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        if (cookieStore[url] == null) {
            cookieStore[url] = mutableListOf()
        }
        cookieStore[url]!!.addAll(cookies)
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore[url] ?: listOf()
    }

    fun getCookies(): List<Cookie> {
        return cookieStore.values.flatten()
    }
}