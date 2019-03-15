package com.project.PizzaExpress.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserDAO {

    @Select("select password from user where username = #{name}")
    List<String> query(String name);
}
