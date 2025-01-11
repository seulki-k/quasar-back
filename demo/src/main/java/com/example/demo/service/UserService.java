package com.example.demo.service;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.FetchUserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.dao.UserRepository;
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
    int row = userMapper.deleteById(id);
    if (row == 0) {
      throw new IllegalArgumentException("선택된 유저가 없습니다.");
    } else {
      System.out.println("User with ID " + id + " deleted successfully.");
    }
  }

  public void updateUser(FetchUserDto fetchUserDto) {
      userMapper.updateUser(fetchUserDto);
  }
}
