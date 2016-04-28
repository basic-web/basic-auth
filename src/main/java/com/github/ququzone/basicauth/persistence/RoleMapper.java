package com.github.ququzone.basicauth.persistence;

import com.github.ququzone.common.MybatisMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * role mapper.
 *
 * @author Yang XuePing
 */
@MybatisMapper
public interface RoleMapper {
    @Insert("insert into role_users(role_id, user_id) values (#{roleId}, #{userId})")
    void insertUserRole(@Param("roleId") String roleId, @Param("userId") String userId);
}
