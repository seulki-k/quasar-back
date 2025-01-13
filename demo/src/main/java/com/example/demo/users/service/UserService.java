package com.example.demo.users.service;

import com.example.demo.users.dto.CreateUserDto;
import com.example.demo.users.dto.FetchUserDto;
import com.example.demo.users.mapper.UserMapper;
import com.example.demo.users.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public List<FetchUserDto> getAllUsers() {
    return userMapper.fetchAllUsers();
  }

  public void addUser(CreateUserDto createUserDto) {
    userRepository.save(createUserDto.toUsers());
  }

  public void deleteUser(Long id) {
    userMapper.deleteById(id);
  }

  public void updateUser(FetchUserDto fetchUserDto) {
    userMapper.updateUser(fetchUserDto);
  }
}
