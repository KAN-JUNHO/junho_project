package com.book.demo.scheduler;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;

@Slf4j
public class Scheduled2Impl implements Scheduled2{

    @Override
    public int fixedDelay() {
        return 10;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
