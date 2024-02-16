package net.catstack.lyrixir.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC

fun decorateLog(message: String) =
    "${MDC.get("requestEndpoint")}: $message | requestId = ${MDC.get("requestId")}"

fun Any.injectLogger(): Logger = LoggerFactory.getLogger(this::class.java)