package com.userorder.mapper;

import com.userorder.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectUser(Integer id);
}
