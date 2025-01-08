package com.example.demo.service;

import com.example.demo.dao.Users;
import com.example.demo.dto.FetchUsers;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<FetchUsers> getAllUsers() {
//        List<Users> users = userRepository.findAll();
//        List<FetchUsers> fetchUsers = new ArrayList<>();
//        for (Users user : users) {
//            fetchUsers.add(new FetchUsers(user.getName(),user.getEmail()));
//        }
//        return fetchUsers; // 모든 사용자 목록을 조회
        return userMapper.fetchAllUsers();
    }

    public void addUser(Users users) {
        userRepository.save(users);
    }

    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
        userMapper.deleteById(id);
    }
}
