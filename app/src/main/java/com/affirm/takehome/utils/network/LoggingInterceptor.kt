package com.affirm.takehome.utils.network

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.http2.Http2Reader.Companion.logger

class LoggingInterceptor : Interceptor {

    companion object {
        const val INITIAL_DELIMITER =
            ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
        const val DELIMITER =
            "================================================================================================================="
    }

    override fun intercept(chain: Chain): Response {

        val request: Request = chain.request()

        val t1 = System.nanoTime()
        logger.info(
            buildString {
                appendLine(" ")
                appendLine(INITIAL_DELIMITER)
                appendLine("SENDING REQUEST: [${request.method.uppercase()}] method to { ${request.url} }")
                appendLine("CONNECTION : ${chain.connection()}")
                appendLine("HTTPS : ${request.isHttps}")
                append("HEADERS : \n${request.headers}")
                append(DELIMITER)
                if (request.method in listOf("POST", "PUT", "PATCH")) {
                    appendLine("BODY : ${request.body}")
                    appendLine(DELIMITER)
                }
                appendLine()
            }
        )

        val response = chain.proceed(request)

        val t2 = System.nanoTime()

        val st = String.format(
            "RECEIVED RESPONSE : for %s in [ %.1fms ]%n",
            response.request.url, (t2 - t1) / 1e6
        )
        logger.info(

            buildString {
                appendLine(" ")
                appendLine(INITIAL_DELIMITER)
                append(st)
                append("HEADERS : \n${response.headers}")
                appendLine("BODY : ${request.body}")
                append(DELIMITER)
                appendLine()
            }
        )

        return response

    }
}
