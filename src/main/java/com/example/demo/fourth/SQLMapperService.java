package com.example.demo.fourth;

import com.example.demo.second.CustomUser;
import com.example.demo.second.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SQLMapperService")
@RequiredArgsConstructor
public class SQLMapperService implements DBService {
    private final UsersMapper usersMapper;
    @Override
    public List<CustomUser> selectUsers() {
        return usersMapper.selectUsers();
    }
}
