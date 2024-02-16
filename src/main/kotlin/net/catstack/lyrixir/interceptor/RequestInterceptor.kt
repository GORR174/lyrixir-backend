package net.catstack.lyrixir.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.catstack.lyrixir.util.decorateLog
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.Exception
import java.util.UUID

@Component
class RequestInterceptor : HandlerInterceptor {
    val logger = LoggerFactory.getLogger(this::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val uid = UUID.randomUUID().toString()

        MDC.put("requestId", uid)
        MDC.put("requestEndpoint", request.requestURI)

        logger.info(decorateLog("Starting request..."))
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        logger.info(decorateLog("Request complete!"))

        MDC.remove("requestId")
        MDC.remove("requestEndpoint")
    }
}