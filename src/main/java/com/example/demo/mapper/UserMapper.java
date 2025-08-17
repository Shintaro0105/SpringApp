package com.example.demo.mapper;

import org.apache.ibatis.annotations.*;
import com.example.demo.model.User;
import java.util.List;

@Mapper
public interface UserMapper {
    
    /**
     * ユーザー名でユーザーを検索
     * Spring Securityの認証で使用される
     */
    // @Select("SELECT user_id, user_name, password, role, enabled, created_at, updated_at " +
    //         "FROM users WHERE user_name = #{userName}")
    // @Results({
    //     @Result(property = "userId", column = "user_id"),
    //     @Result(property = "userName", column = "user_name"),
    //     @Result(property = "password", column = "password"),
    //     @Result(property = "role", column = "role"),
    //     @Result(property = "enabled", column = "enabled"),
    //     @Result(property = "createdAt", column = "created_at"),
    //     @Result(property = "updatedAt", column = "updated_at")
    // })
    User findByUserName(@Param("userName") String userName);
    
    /**
     * 全ユーザーを取得
     */
    // @Select("SELECT user_id, user_name, password, role, enabled, created_at, updated_at " +
    //         "FROM users WHERE enabled = true ORDER BY user_id")
    // @Results({
    //     @Result(property = "userId", column = "user_id"),
    //     @Result(property = "userName", column = "user_name"),
    //     @Result(property = "password", column = "password"),
    //     @Result(property = "role", column = "role"),
    //     @Result(property = "enabled", column = "enabled"),
    //     @Result(property = "createdAt", column = "created_at"),
    //     @Result(property = "updatedAt", column = "updated_at")
    // })
    List<User> findAll();
    
    /**
     * ユーザーIDでユーザーを検索
     */
    // @Select("SELECT user_id, user_name, password, role, enabled, created_at, updated_at " +
    //         "FROM users WHERE user_id = #{userId}")
    // @Results({
    //     @Result(property = "userId", column = "user_id"),
    //     @Result(property = "userName", column = "user_name"),
    //     @Result(property = "password", column = "password"),
    //     @Result(property = "role", column = "role"),
    //     @Result(property = "enabled", column = "enabled"),
    //     @Result(property = "createdAt", column = "created_at"),
    //     @Result(property = "updatedAt", column = "updated_at")
    // })
    User findById(@Param("userId") Integer userId);
    
    /**
     * ユーザーを新規登録
     */
    // @Insert("INSERT INTO users (user_name, password, role, enabled, created_at, updated_at) " +
    //         "VALUES (#{userName}, #{password}, #{role}, #{enabled}, NOW(), NOW())")
    // @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);
    
    /**
     * ユーザー情報を更新
     */
    // @Update("UPDATE users SET " +
    //         "user_name = #{userName}, " +
    //         "password = #{password}, " +
    //         "role = #{role}, " +
    //         "enabled = #{enabled}, " +
    //         "updated_at = NOW() " +
    //         "WHERE user_id = #{userId}")
    int update(User user);
    
    /**
     * ユーザーを削除（論理削除）
     */
    // @Update("UPDATE users SET enabled = false, updated_at = NOW() WHERE user_id = #{userId}")
    int deleteById(@Param("userId") Integer userId);
    
    /**
     * ユーザー名の重複チェック
     */
    // @Select("SELECT COUNT(*) FROM users WHERE user_name = #{userName}")
    int countByUserName(@Param("userName") String userName);
}