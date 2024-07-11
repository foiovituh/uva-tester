package com.github.foiovituh.uvatester.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.github.foiovituh.uvatester.annotations.TestClass;
import com.github.foiovituh.uvatester.annotations.TestMethod;

public class UVATester {
    private Class<?> clazz;
    private static final Logger LOGGER = Logger.getLogger(UVATester.class.getName());
    
    public UVATester(String className) {
        try {
            this.clazz = Class.forName(className);
        } catch (ClassNotFoundException classNotFoundException) {
            ErrorHandler.logAndExit(LOGGER, Level.WARNING, className,
                    classNotFoundException, 1);
        }
    }
    
    public void run() {
        if (clazz.isAnnotationPresent(TestClass.class)) {
            Stream.of(clazz.getDeclaredMethods())
                    .forEach(method -> {
                        if (method.isAnnotationPresent(TestMethod.class)
                                && method.getParameterCount() == 0
                                && method.getReturnType() == void.class
                                && Modifier.isStatic(method.getModifiers())) {
                            try {
                                method.invoke(null);
                            } catch (IllegalAccessException
                                    | IllegalArgumentException
                                    | InvocationTargetException invokeMethodException) {
                                ErrorHandler.logAndExit(LOGGER, Level.SEVERE,
                                        method.getName(), invokeMethodException, 1);
                            }
                        }
                    });
        } else {
            ErrorHandler.handleTestClassAnnotationNotPresent(LOGGER, clazz.getName());
        }
    }
}
