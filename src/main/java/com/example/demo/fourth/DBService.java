package com.example.demo.fourth;

import com.example.demo.second.CustomUser;

import java.util.List;

public interface DBService {
    List<CustomUser> selectUsers();
}
