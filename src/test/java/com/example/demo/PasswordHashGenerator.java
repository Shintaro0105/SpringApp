package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * パスワードハッシュ生成ツール
 * 
 * 使用方法:
 * 1. このファイルをコンパイル・実行
 * 2. 生成されたハッシュをSQLの INSERT文で使用
 * 
 * 実行コマンド例:
 * javac -cp "spring-security-crypto-*.jar" PasswordHashGenerator.java
 * java -cp ".:spring-security-crypto-*.jar" PasswordHashGenerator
 */
public class PasswordHashGenerator {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // 初期ユーザーのパスワード定義
        String[][] users = {
            {"admin", "admin123"},
            {"testuser", "password123"}, 
            {"demo", "demo123"},
            {"system_admin", "system123"},
            {"guest_user", "guest123"},
            {"sales_manager", "sales123"},
            {"dev_manager", "dev123"},
            {"hr_manager", "hr123"}
        };
        
        System.out.println("=".repeat(80));
        System.out.println("Spring Security BCrypt パスワードハッシュ生成ツール");
        System.out.println("=".repeat(80));
        System.out.println();
        
        // 各ユーザーのパスワードをハッシュ化
        for (String[] user : users) {
            String username = user[0];
            String password = user[1];
            String hashedPassword = encoder.encode(password);
            
            System.out.println("ユーザー名: " + username);
            System.out.println("平文パスワード: " + password);
            System.out.println("ハッシュ化パスワード: " + hashedPassword);
            
            // 検証テスト
            boolean matches = encoder.matches(password, hashedPassword);
            System.out.println("検証結果: " + (matches ? "✓ 正常" : "✗ エラー"));
            System.out.println("-".repeat(60));
        }
        
        System.out.println();
        System.out.println("SQL INSERT文生成:");
        System.out.println("=".repeat(40));
        
        // SQL INSERT文を生成
        for (String[] user : users) {
            String username = user[0];
            String password = user[1];
            String hashedPassword = encoder.encode(password);
            String role = username.equals("admin") || username.equals("system_admin") ? "ADMIN" : "USER";
            
            System.out.printf("INSERT INTO users (user_name, password, role, enabled) VALUES ('%s', '%s', '%s', TRUE);%n",
                username, hashedPassword, role);
        }
        
        System.out.println();
        System.out.println("=".repeat(80));
        System.out.println("ハッシュ生成完了！");
        System.out.println("=".repeat(80));
    }
}