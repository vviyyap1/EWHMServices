package com.elasticwave.hotelmgmt.services.account.revenue.query.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@Aspect
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.elasticwave.hotelmgmt.services.account.revenue.query..*)")
    public void applicationPackagePointcut(){}

    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
        if(log.isDebugEnabled()){
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try{
            Object result = joinPoint.proceed();
            if(log.isDebugEnabled()){
                log.debug("Exit: {}.{}() with result = {}",joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(),result);
            }
            return result;
        }catch (Exception e){
            log.error("Error in {}.{}(): {}",
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e);
            throw e;
        }
    }
}
