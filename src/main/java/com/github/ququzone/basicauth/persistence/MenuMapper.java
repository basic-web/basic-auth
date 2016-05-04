package com.github.ququzone.basicauth.persistence;

import com.github.ququzone.basicauth.model.Menu;
import com.github.ququzone.common.MybatisMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * menu mapper.
 *
 * @author Yang XuePing
 */
@MybatisMapper
public interface MenuMapper {
    @Select("select id, name, icon, order_num, created_time, updated_time from menus where id = #{id}")
    @ResultMap("MenuResult")
    Menu find(String id);

    @Select("select id, name, icon, order_num, created_time, updated_time from menus order by order_num")
    @ResultMap("MenuResult")
    List<Menu> all();
}
