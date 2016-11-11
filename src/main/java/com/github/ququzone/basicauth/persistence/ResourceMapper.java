package com.github.ququzone.basicauth.persistence;

import com.github.ququzone.basicauth.model.MenuResource;
import com.github.ququzone.basicauth.model.Resource;
import com.github.ququzone.basicauth.model.ResourceMapping;
import com.github.ququzone.common.MybatisMapper;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * resource mapper.
 *
 * @author Yang XuePing
 */
@MybatisMapper
public interface ResourceMapper {
    @Select("select id, name, pattern, method, created_time, updated_time from resources" +
            " where pattern = #{pattern} and method = #{method}")
    @ResultMap("ResourceResult")
    Resource findByPatternAndMethod(@Param("pattern") String pattern,
                                    @Param("method") ResourceMapping.RequestMethod method);

    @Select("select count(1) from role_resources rr, role_users ru where rr.role_id = ru.role_id " +
            "and rr.resource_id = #{resourceId} and ru.user_id = #{userId}")
    Integer countByUserId(@Param("userId") String userId, @Param("resourceId") String resourceId);

    @Select("select mr.menu_id, mr.order_num, t.* from" +
            " (select distinct r.id, r.name, r.pattern, r.method, r.created_time, r.updated_time" +
            " from resources r, role_resources rr, role_users ru" +
            " where r.id = rr.resource_id and rr.role_id = ru.role_id and ru.user_id = #{userId}) t, menu_resources mr" +
            " where t.id = mr.resource_id order by mr.menu_id, mr.order_num")
    @ResultMap("ResourceOrderResult")
    List<Resource> findUserResources(String userId);

    Long count(@Param("q") String q);

    List<Resource> page(@Param("q") String q, @Param("limit") int limit, @Param("offset") long offset);

    @Insert("insert into resources (id, name, pattern, method, created_time)" +
            " values (#{id}, #{name}, #{pattern}, #{method}, #{createdTime})")
    void insert(Resource resource);

    @Select("select id, name, pattern, method, created_time, updated_time from resources where id = #{id}")
    @ResultMap("ResourceResult")
    Resource find(String id);

    @Update("update resources set name = #{name}, pattern = #{pattern}, method = #{method}," +
            " updated_time = #{updatedTime} where id = #{id}")
    void update(@Param("id") String id, @Param("name") String name, @Param("pattern") String pattern,
                @Param("method") ResourceMapping.RequestMethod method, @Param("updatedTime") Date updatedTime);

    @Delete("delete from role_resources where resource_id = #{resourceId}")
    void deleteResourceRole(@Param("resourceId") String resourceId);

    @Delete("delete from resources where id = #{id}")
    void delete(@Param("id") String id);

    @Select("select r.id, r.name, r.pattern, r.method, r.created_time, r.updated_time from resources r, menu_resources mr" +
            " where r.id = mr.resource_id and mr.menu_id = #{menuId} order by mr.order_num")
    @ResultMap("ResourceResult")
    List<Resource> findMenuResources(@Param("menuId") String menuId);

    @Select("select r.id, r.name, r.pattern, r.method, r.created_time, r.updated_time from resources r" +
            " where r.method = 'GET' and not exists" +
            " (select * from menu_resources mr where r.id = mr.resource_id and mr.menu_id = #{menuId})")
    @ResultMap("ResourceResult")
    List<Resource> findUnMenuResources(@Param("menuId") String menuId);

    @Select("select id, menu_id, resource_id, order_num, created_time, updated_time from menu_resources" +
            " where menu_id = #{menuId} and resource_id = #{resourceId}")
    @ResultMap("MenuResourceResult")
    MenuResource findMenuResource(@Param("menuId") String menuId, @Param("resourceId") String resourceId);

    @Update("update menu_resources set order_num = #{orderNum} where id = #{id}")
    void updateMenuResourceOrderNum(@Param("id") String id, @Param("orderNum") Integer orderNum);

    @Select("select id, name, pattern, method, created_time, updated_time from resources")
    @ResultMap("ResourceResult")
    List<Resource> all();
}
