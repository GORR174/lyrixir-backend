package net.catstack.lyrixir.exception

import java.lang.RuntimeException

abstract class BaseServiceException(message: String?, cause: Throwable?) : RuntimeException(cause) {
    override var message: String = buildMessage(message)

    constructor(message: String) : this(message, null)

    abstract fun getExceptionMessage(): String
    abstract fun getCode(): Int

    private fun buildMessage(message: String?) =
        message?.let { "${getExceptionMessage()}: $message" } ?: getExceptionMessage()
}