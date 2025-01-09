package com.example.demo.mapper;

import com.example.demo.dto.FetchUsersDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
//    @Select("select name,email from users")
    List<FetchUsersDto> fetchAllUsers();

    // 실행한 쿼리의 수 출력
    int deleteById(Long id);

    void updateUser(FetchUsersDto fetchUsersDto);
}
