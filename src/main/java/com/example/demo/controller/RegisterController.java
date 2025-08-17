package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.RegisterForm;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerForm") RegisterForm form, Model model) {

        try {
            // パスワード一致チェック
            if (!form.getPassword().equals(form.getRepeatPassword())) {
                model.addAttribute("errorMessage", "パスワードが一致しません。");
                return "register";
            }
            User user = new User(form.getUserName(), form.getPassword(), null, null);
            // ユーザー名の重複チェック
            if (userService.isUserNameExists(user.getUserName())) {
                model.addAttribute("errorMessage", 
                    "ユーザー名「" + user.getUserName() + "」は既に使用されています。");  
                return "register";     
            }
            // 登録処理
            userService.createUser(user);
            model.addAttribute("successMessage", "アカウントを作成しました！");
            return "login";
        } catch (Exception e) {
            // TODO: handle exception
            model.addAttribute("errorMessage", 
                "ユーザーの作成に失敗しました: " + e.getMessage());
        }
        return "register";
    }
}
