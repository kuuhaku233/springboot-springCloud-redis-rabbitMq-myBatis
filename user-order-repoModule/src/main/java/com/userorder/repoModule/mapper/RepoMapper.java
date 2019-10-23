package com.userorder.repoModule.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RepoMapper {

    Integer updateRepo( @Param("number") Integer number,@Param("id") Integer id);

}
