package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.PizzaTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PizzaTypeDAO {

    @Select("select * from pizza_type where p_name = #{p_name}")
    List<PizzaTypeEntity> query(@Param("p_name") String p_name);
}
