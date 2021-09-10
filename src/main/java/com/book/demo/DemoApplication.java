package com.book.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {

		RegisterCountThread registerCountThread = new RegisterCountThread();
		registerCountThread.start();


		SpringApplication.run(DemoApplication.class, args);

//		Class<JunhoScheduler> clazz = JunhoScheduler.class;
//
//		Method[] methods = clazz.getDeclaredMethods();
//		for(Method method : methods){
//			Scheduled2 scheduled2 = method.getAnnotation(Scheduled2.class);
//			if ( scheduled2 == null )
//				continue;
//
//			try {
//				executeFixedDelaySchedule(method, clazz.newInstance(), scheduled2.fixedDelay());
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//
//		}

	}
}
//
//	private static void executeFixedDelaySchedule(Method method, Object obj, int delayTime){
//
//		boolean isResume = true;
//		long time = (long)delayTime;
//		int cnt=0;
//		while (true) {
//
//			try {
//				method.invoke(obj);
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				e.printStackTrace();
//			}
//
//			try {
//				Thread.sleep(time * 1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			cnt += 1;
//
//			if( cnt == 10)
//				break;
//
//		}
//
//	}

//}
