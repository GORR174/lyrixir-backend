package net.catstack.lyrixir.aspect

import net.catstack.lyrixir.util.decorateLog
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Aspect
@Component
@ConditionalOnProperty(prefix = "lyrixir.logging.service", name = ["enabled"], havingValue = "true")
class ServiceLoggingAspect {
    val logger = LoggerFactory.getLogger(this::class.java)

    @Before("execution(public * net.catstack.lyrixir.service..*.*(..))")
    fun logServiceStart(jp: JoinPoint) {
        logger.info(decorateLog("Service ${jp.target.javaClass.simpleName}: start execution"))
    }

    @After("execution(public * net.catstack.lyrixir.service..*.*(..))")
    fun logServiceEnd(jp: JoinPoint) {
        logger.info(decorateLog("Service ${jp.target.javaClass.simpleName}: stop execution"))
    }
}