package com.example.demo.fourth;

import com.example.demo.second.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service("JDBCService")
@RequiredArgsConstructor
public class JDBCService implements DBService {
    private final JdbcTemplate jdbcTemplate; // SQL Mapper
    @Override
    public List<CustomUser> selectUsers() {
        return jdbcTemplate.query("SELECT * FROM demo_for_bo.users",
                (rs, rowNum) -> new CustomUser(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                )
        );
    }
}
