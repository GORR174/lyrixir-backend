package net.catstack.lyrixir.exception

import lombok.extern.slf4j.Slf4j
import net.catstack.lyrixir.dto.AdapterResponse
import net.catstack.lyrixir.util.decorateLog
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(Exception::class)
    fun unknownException(e: Exception): ResponseEntity<AdapterResponse<Any>> {
        return baseServiceException(InternalAdapterError(e.message, e))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun invalidRequestFormatException(e: Exception): ResponseEntity<AdapterResponse<Any>> {
        return baseServiceException(InvalidRequestFormatError(e.message, e))
    }

    @ExceptionHandler(BaseServiceException::class)
    fun baseServiceException(e: BaseServiceException): ResponseEntity<AdapterResponse<Any>> {
        logger.error(decorateLog("Something went wrong!"), e)

        return ResponseEntity(AdapterResponse.error(e), HttpStatus.OK)
    }
}