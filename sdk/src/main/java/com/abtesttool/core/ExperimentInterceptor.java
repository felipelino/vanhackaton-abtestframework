package com.abtesttool.core;

import com.abtesttool.annotations.AlternativeInfo;
import com.abtesttool.annotations.AlternativeMethod;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Aspect
@Component
public class ExperimentInterceptor {

    private static Logger LOGGER = LoggerFactory.getLogger(ExperimentInterceptor.class);

    public ExperimentInterceptor() {
        super();
    }

    //for any method with @AlternativeMethod, no matter what the return type, name, or arguments are, call this method
    @Around("execution(@com.abtesttool.annotations.AlternativeMethod * *(..)) && @annotation(alternativeMethodAnnotation)")
    public Object captureDetailInfo(ProceedingJoinPoint joinPoint, AlternativeMethod alternativeMethodAnnotation) throws Throwable {

        boolean sendArgs = false;

        int i = 0;
        for(Object arg :  joinPoint.getArgs()) {
            Annotation[] annotations = arg.getClass().getAnnotations();
            for(Annotation annotation : annotations) {
                if(annotation instanceof AlternativeInfo) {
                    AlternativeInfo alternativeInfo = (AlternativeInfo) annotation;
                    if(alternativeInfo.send()) {
                        LOGGER.info(joinPoint.getSignature() + " - args["+i+"]=["+arg+"]");
                    }
                    break;
                }
            }
        }

        //execute the method and get the result
        Object result = joinPoint.proceed();

        if(alternativeMethodAnnotation.sendOutput()) {
            LOGGER.info(joinPoint.getSignature() + " - output=["+result+"]");
        }
        return result;
    }

}
