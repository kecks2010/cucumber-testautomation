package de.mirko_werner.cucumber.pages;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface EndPoint {

    String value() default "";
}
