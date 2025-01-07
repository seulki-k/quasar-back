package com.example.demo.service;

import com.example.demo.dao.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll(); // 모든 사용자 목록을 조회
    }

    public void addUser(Users users) {
        userRepository.save(users);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
