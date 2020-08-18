package com.jk.mapper;

import com.jk.entity.HobbyEntity;
import com.jk.entity.UserEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select us.*, ho.name AS hobbyName from t_user us LEFT JOIN t_hobby ho ON us.hobby_id = ho.id ")
    List<UserEntity> selectUserList();

    @Delete(" delete from t_user where user_id  = #{value} ")
    void deleteUser(Integer userId);

    @Select("select * from t_hobby")
    List<HobbyEntity> findUserTypeList();

    @Insert(" insert into t_user (user_name,gender,hobby_id,birthday) values (#{userName},#{gender},#{hobbyId},#{birthday}) ")
    void saveUser(UserEntity userEntity);
}
