package com.example.demo.service;

import com.example.demo.dao.Users;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.FetchUsersDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public List<FetchUsersDto> getAllUsers() {
//        List<Users> users = userRepository.findAll();
//        List<FetchUsers> fetchUsers = new ArrayList<>();
//        for (Users user : users) {
//            fetchUsers.add(new FetchUsers(user.getName(),user.getEmail()));
//        }
//        return fetchUsers; // 모든 사용자 목록을 조회
    return userMapper.fetchAllUsers();
  }

  public void addUser(CreateUserDto createUserDto) {
    if (createUserDto.getName().equals("") && createUserDto.getEmail().equals("")) {
      throw new IllegalArgumentException("이름 및 이메일을 입력해주세요.");
    } else if (createUserDto.getName().equals("")) {
      throw new IllegalArgumentException("이름을 입력해주세요.");
    } else if (createUserDto.getEmail().equals("")) {
      throw new IllegalArgumentException("이메일을 입력해주세요.");
    } else {
      Users user = new Users();
      user.setName(createUserDto.getName());
      user.setEmail(createUserDto.getEmail());
      userRepository.save(user);
    }
  }

  public void deleteUser(Long id) {
//        userRepository.deleteById(id);
    int row = userMapper.deleteById(id);
    if (row == 0) {
      throw new IllegalArgumentException("선택된 유저가 없습니다.");
    } else {
      System.out.println("User with ID " + id + " deleted successfully.");
    }
  }

  public void updateUser(FetchUsersDto fetchUsersDto) {
    if (fetchUsersDto.getName().equals("") && fetchUsersDto.getEmail().equals("")) {
      throw new IllegalArgumentException("이름 및 이메일을 입력해주세요.");
    } else if (fetchUsersDto.getName().equals("")) {
      throw new IllegalArgumentException("이름을 입력해주세요.");
    } else if (fetchUsersDto.getEmail().equals("")) {
      throw new IllegalArgumentException("이메일을 입력해주세요.");
    } else {
      userMapper.updateUser(fetchUsersDto);
    }

  }
}
