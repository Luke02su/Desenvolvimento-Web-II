package com.pwii.fiis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FiisController {
    @GetMapping("/fiis")
    public String getMethodName() {
        System.out.println("/fiis/index");
        return "/fiis/index"; // endereço da pagina do thymeleaf
    }
}
