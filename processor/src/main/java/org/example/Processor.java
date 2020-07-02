package org.example;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("org.example.MyFirstAnnotation")
public class Processor extends AbstractProcessor {

    public boolean process(final Set<? extends TypeElement> annotations,
                           final RoundEnvironment roundEnv) {

        System.out.println("Processor ran!");

        for (Element element : roundEnv.getElementsAnnotatedWith(MyFirstAnnotation.class)) {
            MyFirstAnnotation firstAnnotation = element.getAnnotation(MyFirstAnnotation.class);

            MySecondAnnotation secondAnnotation = firstAnnotation.secondAnnotation();
        }
        return true;
    }
}
