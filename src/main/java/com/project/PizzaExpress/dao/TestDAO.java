package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.PizzaEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDAO {

    @Insert("insert into pizza(p_type, p_id, p_name, price, is_empty, p_picture, f_id, p_size) "+
            "values(#{type}, 0, #{name}, 0, 0, 0, 0, 0)")
    void save(PizzaEntity pe);
}
