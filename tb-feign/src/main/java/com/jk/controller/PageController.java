package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/toMain")
    public String toMain(){
        return "main";
    }
    @RequestMapping("/toAddUserPage")
    public String toAddUserPage(){

        return "addUser";
    }

}
