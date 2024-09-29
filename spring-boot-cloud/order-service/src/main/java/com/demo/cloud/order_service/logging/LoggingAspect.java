package com.demo.cloud.order_service.logging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
public class LoggingAspect {

    protected Logger getLogger(final JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
    }

    @Pointcut("execution(* com.demo.*.controller.*.*(..))")
    public void restControllerExecution() {
    }

    @Pointcut("execution(* com.groupet2i.*.service.*.*(..))")
    public void serviceExecution() {
    }

    @Pointcut("execution(* com.groupet2i.*.repository.*.*(..))")
    public void daoExecution() {
    }

    @Around("restControllerExecution() || serviceExecution() || daoExecution()")
    @SuppressWarnings("PMD.AvoidCatchingThrowable")
    public Object logMethodAccess(final ProceedingJoinPoint joinPoint) throws Throwable {
        final var log = getLogger(joinPoint);
        final var start = Instant.now();
        try {
            return joinPoint.proceed();
        } finally {
            log.info("The execution of the {}#{} takes {}ms",
                    joinPoint.getSignature().getDeclaringType().getSimpleName(),
                    joinPoint.getSignature().getName(),
                    Duration.between(start, Instant.now()).toMillis());
        }
    }

    @AfterThrowing(value = "execution(public * com.groupet2i.tao.lib..*(..))", throwing = "ex")
    public void handleError(final JoinPoint joinPoint, final Throwable ex) throws Throwable {
        final var log = getLogger(joinPoint);
        log.warn("An error occurred during use case execution with message {}", ex.getMessage());
        final var element = ex.getStackTrace()[0];
        log.warn("Exception originated in method: {}.{}", element.getClassName(), element.getMethodName());
        throw ex;
    }
}

