package com.example.demo.users.mapper;

import com.example.demo.users.dto.FetchUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

  //    @Select("select name,email from users")
  List<FetchUserDto> fetchAllUsers();

  // 실행한 쿼리의 수 출력
  void deleteById(Long id);

  void updateUser(FetchUserDto fetchUserDto);
}
