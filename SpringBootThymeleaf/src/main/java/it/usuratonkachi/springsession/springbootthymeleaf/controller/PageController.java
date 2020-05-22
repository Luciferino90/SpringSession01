package it.usuratonkachi.springsession.springbootthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }

}
