package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

import com.example.demo.model.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> selectMany() throws DataAccessException;
}
