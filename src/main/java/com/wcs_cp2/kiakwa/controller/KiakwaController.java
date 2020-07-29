package com.wcs_cp2.kiakwa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class KiakwaController {

    @GetMapping("/")
    private String goHome() {

        return "index";
    }

}