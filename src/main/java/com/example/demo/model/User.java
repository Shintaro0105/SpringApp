package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    private Integer userId;
    private String userName;
    private String password;
    private String role;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // MyBatis用のコンストラクタ（IDなし）
    public User(String userName, String password, String role, Boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }
}