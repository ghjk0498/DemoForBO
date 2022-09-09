package com.example.demo.second;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {
	List<CustomUser> selectUsers();
    CustomUser selectUsersByUsername(String username);
    int insertUsers(CustomUser customUser);
}
