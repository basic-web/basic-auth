package com.github.ququzone.basicauth.persistence;

import com.github.ququzone.basicauth.model.Resource;
import com.github.ququzone.common.MybatisMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * resource mapper.
 *
 * @author Yang XuePing
 */
@MybatisMapper
public interface ResourceMapper {
    @Select("select id, name, pattern, created_time, updated_time from resources where pattern = #{pattern}")
    @ResultMap("ResourceResult")
    Resource findByPattern(@Param("pattern") String pattern);

    @Select("select count(1) from role_resources rr, role_users ru where rr.role_id = ru.role_id " +
            "and rr.resource_id = #{resourceId} and ru.user_id = #{userId}")
    Integer countByUserId(@Param("userId") String userId, @Param("resourceId") String resourceId);

    @Select("select mr.menu_id, mr.order_num, t.* from" +
            " (select distinct r.id, r.name, r.pattern, r.created_time, r.updated_time" +
            " from resources r, role_resources rr, role_users ru" +
            " where r.id = rr.resource_id and rr.role_id = ru.role_id and ru.user_id = #{userId}) t, menu_resources mr" +
            " where t.id = mr.resource_id order by mr.menu_id, mr.order_num")
    @ResultMap("ResourceOrderResult")
    List<Resource> findUserResources(String userId);

    @Select("select count(1) from resources")
    long count();

    @Select("select id, name, pattern, created_time, updated_time from resources" +
            " order by created_time desc limit #{limit} offset #{offset}")
    @ResultMap("ResourceResult")
    List<Resource> page(@Param("limit") int limit, @Param("offset") long offset);
}
