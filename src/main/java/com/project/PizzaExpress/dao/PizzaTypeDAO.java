package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.PizzaTypeEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PizzaTypeDAO {
    @Insert("insert into pizza_type(p_name, resource) "+
            "values(#{p_name}, #{resource})")
    void insert(PizzaTypeEntity pte);

    @Select("select * from pizza_type where p_name = #{p_name}")
    List<PizzaTypeEntity> query(@Param("p_name") String p_name);

    @Update("update pizza_type set resource = #{resource} where p_name = #{p_name}")
    int updatePizzaType(PizzaTypeEntity pte);

    @Delete("delete from pizza_type where p_name = #{p_name}")
    void delete(String p_name);
}
