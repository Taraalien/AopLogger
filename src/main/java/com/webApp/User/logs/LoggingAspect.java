package com.webApp.User.logs;


import com.webApp.User.user.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private AsyncLogger asyncLogger;

    @Before("execution(* com.webApp.User.user.UserService.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Log logEntity = createLogEntity(methodName, args);
        asyncLogger.log(logEntity);
    }

    @AfterReturning(pointcut = "execution(* com.webApp.User.user.UserService.*(..))", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, String result) {
        String methodName = joinPoint.getSignature().getName();
        Log logEntity = createLogEntity(methodName, null);
        logEntity.setResult(result);
        asyncLogger.log(logEntity);
    }

    @AfterThrowing(pointcut = "execution(* com.webApp.User.user.UserService.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        Log logEntity = createLogEntity(methodName, null);
        logEntity.setException(exception.toString());
        asyncLogger.log(logEntity);
    }



    private Log createLogEntity(String methodName, Object[] args) {
        User user=new User();
        Log logEntity = new Log();
        logEntity.setTimestamp(new Date());
        logEntity.setMethodName(methodName);
        logEntity.setArguments(args != null ? args : new Object[0]);
        logEntity.setUsername(user.getUserName());
        return logEntity;
    }

}