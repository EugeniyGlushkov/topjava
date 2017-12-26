package ru.javawebinar.topjava.web;

import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.javawebinar.topjava.web.json.JsonUtil.writeIgnoreProps;

public class AbstractTestData {
    public static <T> ResultMatcher contentJson(T... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "registered", "user"));
    }

    public static <T> ResultMatcher contentJson(T expected) {
        return content().json(writeIgnoreProps(expected, "registered", "user"));
    }
}
