package net.catstack.lyrixir.exception

class InternalAdapterError(message: String? = null, cause: Throwable? = null) : BaseServiceException(message, cause) {
    override fun getExceptionMessage(): String = "Internal Adapter Error"
    override fun getCode(): Int = 1
}