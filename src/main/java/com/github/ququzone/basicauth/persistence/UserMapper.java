package com.github.ququzone.basicauth.persistence;

import com.github.ququzone.basicauth.model.User;
import com.github.ququzone.common.MybatisMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

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
}
