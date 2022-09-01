package com.example.demo.second;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    UserVO selectUsersById(String username);
}
