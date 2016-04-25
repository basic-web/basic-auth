package com.github.ququzone.basicauth.persistence;

import com.github.ququzone.basicauth.model.UserFact;
import com.github.ququzone.common.MybatisMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

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
}
