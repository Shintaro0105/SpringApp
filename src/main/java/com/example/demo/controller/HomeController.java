package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //userListのアドレスにアクセスした際にGetメソッドを実行。
    @GetMapping("/userList")
    public String getUserList(Model model) {
    	
    	//template配下のファイル名を指定することでViewを呼び出せる。    	
        return "userList";
    }
}
