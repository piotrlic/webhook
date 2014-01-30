package com.tomtom.ejb.helloworld;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Target(value={ElementType.FIELD, ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
@Qualifier
public @interface English {

}
