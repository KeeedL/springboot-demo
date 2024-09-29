package com.book.demo.logging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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

    @Pointcut("execution(* com.book.demo.controller.*.*(..))")
    public void restControllerExecution() {
    }

    @Pointcut("execution(* com.book.demo.service.*.*(..))")
    public void serviceExecution() {
    }

    @Pointcut("execution(* com.book.demo.mapper.*.*(..))")
    public void mapperExecution() {
    }

    @Pointcut("execution(* com.book.demo.repository.*.*(..))")
    public void daoExecution() {
    }

    @Around("restControllerExecution() || serviceExecution() || mapperExecution() || daoExecution()")
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
}
