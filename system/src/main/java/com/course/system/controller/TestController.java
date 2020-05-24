package com.course.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex CHEN
 * @version 1.0
 * @since 2020-05-23 21:18
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "OK!!!!!";
    }
}
