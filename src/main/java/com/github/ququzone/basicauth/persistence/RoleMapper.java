package com.github.ququzone.basicauth.persistence;

import com.github.ququzone.basicauth.model.Resource;
import com.github.ququzone.basicauth.model.Role;
import com.github.ququzone.basicauth.model.User;
import com.github.ququzone.common.MybatisMapper;
import org.apache.ibatis.annotations.*;

import java.util.Date;
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
    void deleteUserRoles(@Param("userId") String userId);

    @Select("select count(1) from roles")
    Long count();

    @Select("select id, name, created_time, updated_time from roles" +
            " order by created_time desc limit #{limit} offset #{offset}")
    @ResultMap("RoleResult")
    List<Role> page(@Param("limit") int limit, @Param("offset") long offset);

    @Insert("insert into roles (id, name, created_time) values (#{id}, #{name}, #{createdTime})")
    void insert(Role role);

    @Select("select id, name, created_time, updated_time from roles where id = #{id}")
    @ResultMap("RoleResult")
    Role find(@Param("id") String id);

    @Update("update roles set name = #{name}, updated_time = #{updatedTime} where id = #{id}")
    void update(@Param("id") String id, @Param("name") String name, @Param("updatedTime") Date updatedTime);

    @Delete("delete from roles where id = #{id}")
    void delete(@Param("id") String id);

    @Delete("delete from role_users where role_id = #{roleId}")
    void deleteRoleUser(@Param("roleId") String roleId);

    @Select("select r.id, r.name, r.pattern, r.method, r.created_time, r.updated_time from resources r, role_resources rr" +
            " where r.id = rr.resource_id and rr.role_id = #{roleId}")
    @ResultMap("ResourceResult")
    List<Resource> findRoleResources(@Param("roleId") String roleId);

    @Select("select u.id, u.username, u.status, u.created_time, u.updated_time from users u, role_users ru" +
            " where u.id = ru.user_id and ru.role_id = #{roleId}")
    @ResultMap("UserResult")
    List<User> findRoleUsers(@Param("roleId") String roleId);

    @Delete("delete from role_resources where role_id = #{roleId}")
    void deleteRoleResource(@Param("roleId") String roleId);

    @Select("select r.id, r.name, r.created_time, r.updated_time from roles r, role_resources rr" +
            " where r.id = rr.role_id and rr.resource_id = #{resourceId}")
    @ResultMap("RoleResult")
    List<Role> resourceRoles(@Param("resourceId") String resourceId);

    @Delete("delete from role_resources where resource_id = #{resourceId}")
    void deleteResourceRole(@Param("resourceId") String resourceId);

    @Insert("insert into role_resources(role_id, resource_id) values (#{roleId}, #{resourceId})")
    void insertResourceRole(@Param("roleId") String roleId, @Param("resourceId") String resourceId);

    @Select("select count(1) from role_users where role_id = #{roleId} and user_id = #{userId}")
    Long countUserRole(@Param("roleId") String roleId, @Param("userId") String userId);

    @Delete("delete from role_users where role_id = #{roleId} and user_id = #{userId}")
    void deleteUserRole(@Param("roleId") String roleId, @Param("userId") String userId);

    @Select("select id, name, created_time, updated_time from roles where name = #{name}")
    @ResultMap("RoleResult")
    Role findByName(@Param("name") String name);
}
