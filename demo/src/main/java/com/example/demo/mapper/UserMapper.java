package com.example.demo.mapper;

import com.example.demo.dao.Users;
import com.example.demo.dto.FetchUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
//    @Select("select name,email from users")
    List<FetchUsers> fetchAllUsers();

    void deleteById(Long id);

    void updateUser(FetchUsers fetchUsers);
}
