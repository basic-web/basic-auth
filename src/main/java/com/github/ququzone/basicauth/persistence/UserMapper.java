package com.github.ququzone.basicauth.persistence;

import com.github.ququzone.basicauth.model.User;
import com.github.ququzone.common.MybatisMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * user model mapper.
 *
 * @author Yang XuePing
 */
@MybatisMapper
public interface UserMapper {
    @Select("select id, username, password, status, created_time, updated_time from users where id = #{id}")
    @ResultMap("UserResult")
    User find(@Param("id") String id);

    @Select("select id, username, password, status, created_time, updated_time from users where username = #{username} and password = #{password}")
    @ResultMap("UserResult")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Update("update users set password = #{password}, updated_time = #{updatedTime} where id = #{id}")
    void updatePassword(@Param("id") String id, @Param("password") String password, @Param("updatedTime") Date updatedTime);

    @Select("select count(1) from users")
    long count();

    @Select("select id, username, password, status, created_time, updated_time from users order by created_time desc limit #{limit} offset #{offset}")
    @ResultMap("UserResult")
    List<User> page(@Param("limit") int limit, @Param("offset") long offset);
}
