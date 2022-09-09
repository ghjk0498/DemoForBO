package com.example.demo.fourth;

import com.example.demo.second.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ORMService")
@RequiredArgsConstructor
public class ORMService implements DBService {
    private final UserRepository userRepository;
    @Override
    public List<CustomUser> selectUsers() {
        return userRepository.findAll();
    }
	@Override
	public int insertUsers(CustomUser user) {
		return userRepository.save(user) == null ? 1 : -1;
	}
}
