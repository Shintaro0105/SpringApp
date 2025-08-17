package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.UserService;
import com.example.demo.model.User;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    /**
     * ログイン画面を表示
     * Spring Securityが自動的にログイン処理を行うため、フォームの表示のみを担当
     */
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        
        // エラーメッセージの設定
        if (error != null) {
            model.addAttribute("errorMessage", "ユーザー名またはパスワードが間違っています。");
        }
        
        // ログアウトメッセージの設定
        if (logout != null) {
            model.addAttribute("logoutMessage", "正常にログアウトしました。");
        }
        
        return "login";
    }
    
    /**
     * ユーザー一覧画面を表示
     */
    @GetMapping("/userList")
    public String showUserList(Model model) {
        // 現在ログインしているユーザー情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        
        // MyBatisを使用してユーザー一覧を取得
        List<User> userList = userService.getAllUsers();
        User currentUser = userService.findByUserName(currentUserName);
        
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("userList", userList);
        model.addAttribute("message", "ログインに成功しました！");

        // model.addAttribute("password", passwordEncoder.encode("password"));
        
        return "userList";
    }
    
    /**
     * ホーム画面（ダッシュボード）
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/userList";
    }
}