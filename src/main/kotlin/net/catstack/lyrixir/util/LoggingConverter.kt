package net.catstack.lyrixir.util

import ch.qos.logback.classic.pattern.ClassicConverter
import ch.qos.logback.classic.spi.ILoggingEvent
import org.slf4j.MDC
import java.lang.StringBuilder

class LoggingConverter : ClassicConverter() {
    override fun convert(event: ILoggingEvent?): String {
        val sb = StringBuilder()
        val requestId = MDC.get("requestId")
        val requestEndpoint = MDC.get("requestEndpoint")

        if (requestEndpoint != null && requestId != null) {
            sb.append("-- $requestEndpoint: requestId: $requestId\n")
        }

        return sb.toString()
    }
}