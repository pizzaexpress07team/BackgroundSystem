package com.project.PizzaExpress.DAO;

import com.project.PizzaExpress.Entity.PizzaEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;

@Mapper
public interface PizzaDAO {

    @Insert("insert into pizza(p_type, p_id, p_name, price, is_empty, p_picture, f_id, p_size) "+
            "values(0, 0, #{name}, 0, 0, 0, 0, 0)")
    int save(PizzaEntity pe);
}
