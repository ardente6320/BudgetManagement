package com.oliver.toy.budgetmanagementapi.aspects;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oliver.toy.budgetmanagementapi.utils.LogUtil;
import com.oliver.toy.budgetmanagementapi.utils.ResponseTemplate;

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
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * API 로거
 */
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
            log.info("response :: {} {}", result.getStatusCode(),(ResponseTemplate)result.getBody());
        }
        return result;
    }

    @Pointcut("within(com.oliver.toy.budgetmanagementapi.handlers..*)")
    public void onGlobalExceptionRequest() {}

    @Around("onGlobalExceptionRequest()")
    public Object logGlobalException(ProceedingJoinPoint joinPoint) throws Throwable{
        ResponseEntity<?> result = null;
          
        Object[] args = joinPoint.getArgs();
        Exception e = null;
        HttpServletRequest request = null;

        try {
            for(Object arg : args){
              if(arg instanceof Exception) e = (Exception) arg;
              else if(arg instanceof WebRequest) request = ((ServletWebRequest) arg).getRequest();
            }

            result = (ResponseEntity<?>) joinPoint.proceed(args);
        } finally {
            log.error("Exception :: {} {} < {} {} {}", result.getStatusCode(),request.getRequestURI(), request.getRemoteHost()
                                                            ,(ResponseTemplate)result.getBody(),LogUtil.getStackTrace(e));
        }
        return result;
    }
}
