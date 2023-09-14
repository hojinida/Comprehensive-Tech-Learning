package com.demo.initial.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    @GetMapping("/logTest")
    public void test(){
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("TestLog!!");
        log.trace("trace");
    }
}
