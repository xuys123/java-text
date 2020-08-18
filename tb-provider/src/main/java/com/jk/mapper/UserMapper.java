package com.jk.mapper;

import com.jk.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select us.*, ho.name AS hobbyName from t_user us LEFT JOIN t_hobby ho ON us.hobby_id = ho.id")
    List<UserEntity> findUserList();
}
