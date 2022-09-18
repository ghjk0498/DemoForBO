package com.example.demo.fifth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class FifthController {
    @GetMapping({"/fifth", "/fifth/"})
    public String fifthPage() {
        log.info("info");
        log.debug("debug");
        return "/fifth/index";
    }
}
