package com.oliver.toy.budgetmanagementapi.aspects;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {
    private Map<String,Object> params(JoinPoint joinPoint) {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            params.put(parameterNames[i], args[i]);
        }
        return params;
    }

    @Pointcut("within(com.oliver.toy.budgetmanagementapi.controllers..*)")
    public void onRequest() {}
    
    @Around("onRequest()")
    public Object logAction(ProceedingJoinPoint joinPoint) throws Throwable{
        HttpServletRequest request =(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
        ResponseEntity<?> result = null;
          
        long start = System.currentTimeMillis();
        try {
            result = (ResponseEntity<?>) joinPoint.proceed(joinPoint.getArgs());
        } finally {
            
            long end = System.currentTimeMillis();
            log.debug("Request: {} {} < {} ({}ms)", request.getMethod(), request.getRequestURI(), request.getRemoteHost(), end - start);
            log.info("parameters :: {}", params(joinPoint));
            log.info("response :: {} {}", result.getStatusCode(),result.getBody().toString());
        }
        return result;
    }
}
