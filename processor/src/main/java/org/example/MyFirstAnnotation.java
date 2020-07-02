package org.example;

public @interface MyFirstAnnotation {
    MySecondAnnotation secondAnnotation() default @MySecondAnnotation;
}
