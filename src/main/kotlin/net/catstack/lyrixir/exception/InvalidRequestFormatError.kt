package net.catstack.lyrixir.exception

class InvalidRequestFormatError(message: String? = null, cause: Throwable? = null) : BaseServiceException(message, cause) {
    override fun getExceptionMessage(): String = "Invalid Request Format"
    override fun getCode(): Int = 2
}