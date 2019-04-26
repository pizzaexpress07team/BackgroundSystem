package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.PizzaEntity;
import com.project.PizzaExpress.entity.PizzaWithResEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PizzaDAO {

    @Insert("insert into pizza(p_type, p_id, p_name, price, is_empty, p_picture, f_id, p_size) "+
            "values(#{p_type}, #{p_id}, #{p_name}, #{price}, #{is_empty}, #{p_picture}, #{f_id}, #{p_size})")
    void insert(PizzaEntity pe);

    @Select("select * from pizza")
    List<PizzaEntity> queryAll();

    @Select("select * from pizza where p_id = #{p_id}")
//    @Results({
//            @Result(property = "p_type", column = "p_type"),
//            @Result(property = "p_id", column = "p_id"),
//            @Result(property = "p_name", column = "p_name"),
//            @Result(property = "price", column = "price"),
//            @Result(property = "is_empty", column = "is_empty"),
//            @Result(property = "p_picture", column = "p_picture"),
//            @Result(property = "f_id", column = "f_id"),
//            @Result(property = "p_size", column = "p_size")
//    })
    List<PizzaEntity> queryPizzaInfo(String p_id);

    @Select("select * from pizza natural join pizza_type")
    List<PizzaWithResEntity> queryAllWithRes();

    @Select("select * from pizza natural join pizza_type where p_id = #{p_id}")
    List<PizzaWithResEntity> queryPizzaInfoWithRes(String p_id);

    @Select("select * from pizza natural join pizza_type where p_name like #{p_name}")
    List<PizzaWithResEntity> queryPizzaInfoWithResLike(String p_name);
}
