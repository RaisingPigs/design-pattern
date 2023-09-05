package com.pan.statemachine.annotation;

import com.pan.statemachine.enums.SyncTaskVersionStates;
import org.springframework.statemachine.annotation.OnTransition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnTransition
public @interface StatesOnTransition {
    SyncTaskVersionStates[] source() default {};
    SyncTaskVersionStates[] target() default {};
}