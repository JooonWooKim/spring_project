package com.cos.new_project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
 
    @Before("execution(* com.cos.new_project.service.*.회원*(..))")
    public void loggerBefore() {
        System.out.println("회원으로 시작하는 메서드가 시작됩니다.");
    }
 
    @After("execution(* com.cos.new_project.service.*.회원*(..))")
    public void loggerAfter() {
        System.out.println("회원으로 시작하는 메서드가 끝났습니다.");
    }
 
    @Around("execution(* com.cos.new_project.controller.*.Controller.*(..))")
    public Object loggerAround(ProceedingJoinPoint pjp) throws Throwable {
        long beforeTimeMillis = System.currentTimeMillis();
        System.out.println("[UserController] 실행 시작 : "
                        +pjp.getSignature().getDeclaringTypeName() + "."
                        +pjp.getSignature().getName());
        Object result = pjp.proceed();
 
        long afterTimeMillis = System.currentTimeMillis() - beforeTimeMillis;
        System.out.println("[UserController] 실행 완료: " + afterTimeMillis + "밀리초 소요"
                        +pjp.getSignature().getDeclaringTypeName() + "."
                        +pjp.getSignature().getName());
 
        return result;
 
    }
 
 
}