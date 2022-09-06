package com.example.demo.fourth;

import com.example.demo.second.CustomUser;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Setter
@RequiredArgsConstructor
public class DBController {

    private DBService dbService;
    private final ApplicationContext applicationContext;

    @GetMapping({"/fourth", "/fourth/"})
    public String page() {
        return "/fourth/dbPage";
    }

    @GetMapping("/fourth/users")
    @ResponseBody
    public List<CustomUser> getUsers(@RequestParam String method) {
        if (applicationContext.containsBean(method)) {
            setDbService((DBService) applicationContext.getBean(method));
        } else {
            setDbService((DBService) applicationContext.getBean("SQLMapperService"));
        }
        return dbService.selectUsers();
    }
}
