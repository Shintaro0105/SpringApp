package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import com.example.demo.model.User;

@Mapper
public interface UserMapper {
    List<User> selectMany() throws DataAccessException;
}
