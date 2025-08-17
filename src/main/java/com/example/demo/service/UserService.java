package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.mapper.UserMapper;

import java.util.List;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * 全ユーザー一覧を取得
     */
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }
    
    /**
     * ユーザーIDでユーザーを検索
     */
    @Transactional(readOnly = true)
    public User findById(Integer userId) {
        return userMapper.findById(userId);
    }
    
    /**
     * ユーザー名でユーザーを検索
     */
    @Transactional(readOnly = true)
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }
    
    /**
     * ユーザーを新規登録
     */
    public User createUser(User user) {
        // パスワードをハッシュ化
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        
        // デフォルト値の設定
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        if (user.getEnabled() == null) {
            user.setEnabled(true);
        }
        
        // データベースに挿入
        int result = userMapper.insert(user);
        if (result > 0) {
            return user; // MyBatisが自動でIDを設定
        } else {
            throw new RuntimeException("ユーザーの作成に失敗しました");
        }
    }
    
    /**
     * ユーザー情報を更新
     */
    public User updateUser(User user) {
        User existingUser = userMapper.findById(user.getUserId());
        if (existingUser == null) {
            throw new RuntimeException("更新対象のユーザーが存在しません: " + user.getUserId());
        }
        
        // パスワードが変更される場合はハッシュ化
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // パスワードが空の場合は既存のパスワードを維持
            user.setPassword(existingUser.getPassword());
        }
        
        int result = userMapper.update(user);
        if (result > 0) {
            return userMapper.findById(user.getUserId());
        } else {
            throw new RuntimeException("ユーザーの更新に失敗しました");
        }
    }
    
    /**
     * ユーザーを削除（論理削除）
     */
    public boolean deleteUser(Integer userId) {
        User existingUser = userMapper.findById(userId);
        if (existingUser == null) {
            throw new RuntimeException("削除対象のユーザーが存在しません: " + userId);
        }
        
        int result = userMapper.deleteById(userId);
        return result > 0;
    }
    
    /**
     * ユーザー名の重複チェック
     */
    @Transactional(readOnly = true)
    public boolean isUserNameExists(String userName) {
        return userMapper.countByUserName(userName) > 0;
    }
    
    /**
     * パスワードの妥当性をチェック
     */
    public boolean isPasswordValid(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}