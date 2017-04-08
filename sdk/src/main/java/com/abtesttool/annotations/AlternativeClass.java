package com.abtesttool.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AlternativeClass {

    String experimentId();
    String alternativeId();
    float probability() default 0.5f;
}
