package com.example.myapplication2.annotion;


import androidx.annotation.IdRes;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectView {

    //用元注解限定只能是idres
    @IdRes int value();
}
