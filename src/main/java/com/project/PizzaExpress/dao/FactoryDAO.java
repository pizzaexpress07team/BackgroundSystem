package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.FactoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FactoryDAO {

    @Select("select * from factory where f_id = #{f_id}")
    List<FactoryEntity> query(String f_id);

    @Select("select * from factory")
    List<FactoryEntity> queryAll();
}
