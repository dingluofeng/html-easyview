package com.eason.html.easyview.core.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Documented
@Retention(RUNTIME)
@Target({
    TYPE})
@RequestMapping
@Controller
public @interface TableViewController {

    @AliasFor(annotation = RequestMapping.class, value = "value")
    String[] value();
}
