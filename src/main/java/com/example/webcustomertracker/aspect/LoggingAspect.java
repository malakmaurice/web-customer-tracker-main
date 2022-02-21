package com.example.webcustomertracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    //setup logger
    private Logger logger=Logger.getLogger(getClass().getName());
    //pointcut decliration
    @Pointcut("execution(* com.example.webcustomertracker.controller.*.*(..))")
    private void controllerPointCut(){}

    @Pointcut("execution(* com.example.webcustomertracker.dao.*.*(..))")
    private void daoPointCut(){}

    @Pointcut("daoPointCut()|| controllerPointCut()")
    private void appFlowPointCut(){}

    // before advice
    @Before("appFlowPointCut()")
    public void  before(JoinPoint joinPoint){
        // display method signature
        MethodSignature methodSignature=(MethodSignature)joinPoint.getSignature();
        logger.info("===> @Before Advice calling method : " +methodSignature);

        //display args
        Object[] args=joinPoint.getArgs();
        for(Object arg :args){
            logger.info("===>Argument : "+arg);
        }
    }

}
