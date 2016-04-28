package com.github.ququzone.basicauth.persistence;

import com.github.ququzone.basicauth.model.UserFact;
import com.github.ququzone.common.MybatisMapper;
import org.apache.ibatis.annotations.*;

import java.util.Date;

/**
 * user fact mapper.
 *
 * @author Yang XuePing
 */
@MybatisMapper
public interface UserFactMapper {
    @Select("select id, user_id, name, value, created_time, updated_time from user_facts" +
            " where user_id = #{userId} and name = #{name}")
    @ResultMap("UserFactResult")
    UserFact findByUserIdAndName(@Param("userId") String userId, @Param("name") UserFact.Field name);

    @Update("update user_facts set value = #{value}, updated_time = #{updatedTime} where user_id = #{userId} and name = #{name}")
    void updateValueByUserId(@Param("userId") String userId, @Param("name") UserFact.Field name,
                             @Param("value") String value, @Param("updatedTime") Date updatedTime);

    @Insert("insert into user_facts(id, user_id, name, value, created_time)" +
            " values (#{id}, #{userId}, #{name}, #{value}, #{createdTime})")
    void insert(UserFact fact);
}
