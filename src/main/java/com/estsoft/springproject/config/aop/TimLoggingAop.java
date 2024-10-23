package com.estsoft.springproject.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimLoggingAop {
    //특정 메서드 호출 시 , 수행 시간 측정
    @Around("execution(* com.estsoft.springproject.book..*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();  // 실행 시간 측정 시작
            return joinPoint.proceed();  // 실제 메소드 실행
        } finally {
            stopWatch.stop();  // 실행 시간 측정 종료
            String methodName = joinPoint.getSignature().getName();  // 실행된 메소드 이름
            System.out.println("Method [" + methodName + "] executed in " + stopWatch.getTotalTimeMillis() + " ms");
        }

    }
}
