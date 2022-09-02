package com.example.demo.second;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    CustomUser selectUsersByUsername(String username);
    int insertUsers(CustomUser customUser);
}
