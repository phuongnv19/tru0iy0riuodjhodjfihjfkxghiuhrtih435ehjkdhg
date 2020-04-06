package com.toprate.erp.base.anotation;

/**
 * 
 * @author Nam, Nguyen Hoai
 *
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;  
  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD, ElementType.FIELD})  
public @interface AdjHistory {  
	String field() default "";
}  
