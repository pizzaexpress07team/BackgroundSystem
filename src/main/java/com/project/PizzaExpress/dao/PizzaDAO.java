package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.PizzaEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface PizzaDAO {

    @Insert("insert into pizza(p_type, p_id, p_name, price, is_empty, p_picture, f_id, p_size) "+
            "values(#{type}, #{id}, #{name}, #{price}, #{is_empty}, #{picture}, #{fId}, #{size})")
    void save(PizzaEntity pe);

    @Select("select * from pizza")
    List<PizzaEntity> queryAll();
}
