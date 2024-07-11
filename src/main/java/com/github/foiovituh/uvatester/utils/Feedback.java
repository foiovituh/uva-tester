package com.github.foiovituh.uvatester.utils;

public final class Feedback {
    private Feedback() {
    }

    public static void showResult(String testName, String testResult) {
        System.out.println(testName + " => " + testResult);
    }
}