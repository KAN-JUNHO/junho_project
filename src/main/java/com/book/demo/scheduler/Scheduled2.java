package com.book.demo.scheduler;

import org.springframework.scheduling.annotation.Schedules;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scheduled2{

    int fixedDelay() default 0;
}
