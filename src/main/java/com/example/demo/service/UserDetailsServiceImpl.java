package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.mapper.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * Spring Securityの認証で使用されるメソッド
     * ユーザー名からユーザー情報を取得し、UserDetailsオブジェクトを返す
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // MyBatisのMapperを使用してデータベースからユーザー情報を取得
        User user = userMapper.findByUserName(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません: " + username);
        }
        
        if (!user.getEnabled()) {
            throw new UsernameNotFoundException("ユーザーアカウントが無効化されています: " + username);
        }

        // Spring SecurityのUserDetailsオブジェクトを作成して返す
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword()) // データベースにはハッシュ化されたパスワードが保存されている前提
                .roles(user.getRole() != null ? user.getRole() : "USER") // ロールを設定
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!user.getEnabled()) // enabledフィールドに基づいて設定
                .build();
    }
}