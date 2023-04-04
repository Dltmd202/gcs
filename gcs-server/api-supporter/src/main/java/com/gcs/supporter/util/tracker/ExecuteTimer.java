package com.gcs.supporter.util.tracker;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExecuteTimer {

    @Pointcut("@annotation(com.gcs.supporter.util.tracker.TimeTracker)")
    private void timer() { }

    @Around("timer()")
    public Object AssumeExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object res = joinPoint.proceed();

        long end = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getName();
        String methodName = signature.getMethod().getName();

        log.debug("{}-{}, 실행시간 = {}ms", className, methodName, end - start);

        return res;
    }
}
