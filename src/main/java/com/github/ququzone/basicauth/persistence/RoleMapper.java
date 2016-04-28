package com.github.ququzone.basicauth.persistence;

import com.github.ququzone.basicauth.model.Role;
import com.github.ququzone.common.MybatisMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * role mapper.
 *
 * @author Yang XuePing
 */
@MybatisMapper
public interface RoleMapper {
    @Insert("insert into role_users(role_id, user_id) values (#{roleId}, #{userId})")
    void insertUserRole(@Param("roleId") String roleId, @Param("userId") String userId);

    @Select("select id, name, created_time, updated_time from roles order by created_time")
    @ResultMap("RoleResult")
    List<Role> all();

    @Select("select r.id, r.name, r.created_time, r.updated_time from roles r, role_users ru" +
            " where r.id = ru.role_id and ru.user_id = #{userId}")
    @ResultMap("RoleResult")
    List<Role> userRoles(@Param("userId") String userId);

    @Delete("delete from role_users where user_id = #{userId}")
    void deleteUserRole(@Param("userId") String userId);

    @Select("select count(1) from roles")
    long count();

    @Select("select id, name, created_time, updated_time from roles" +
            " order by created_time desc limit #{limit} offset #{offset}")
    @ResultMap("RoleResult")
    List<Role> page(@Param("limit") int limit, @Param("offset") long offset);
}
