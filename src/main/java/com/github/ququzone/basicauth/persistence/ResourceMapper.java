package com.github.ququzone.basicauth.persistence;

import com.github.ququzone.basicauth.model.Resource;
import com.github.ququzone.common.MybatisMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * resource mapper.
 *
 * @author Yang XuePing
 */
@MybatisMapper
public interface ResourceMapper {
    @Select("select id, name, pattern, status, created_time, updated_time from resources where pattern = #{pattern}")
    @ResultMap("ResourceResult")
    Resource findByPattern(@Param("pattern") String pattern);

    @Select("select count(1) from role_resources rr, role_users ru where rr.role_id = ru.role_id " +
            "and rr.resource_id = #{resourceId} and ru.user_id = #{userId}")
    Integer countByUserId(@Param("userId") String userId, @Param("resourceId") String resourceId);
}
