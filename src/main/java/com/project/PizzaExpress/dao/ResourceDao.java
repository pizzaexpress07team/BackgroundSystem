package com.project.PizzaExpress.dao;


import com.project.PizzaExpress.entity.FactoryResEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResourceDao {
    @Select("SELECT * FROM factory_resource JOIN resource ON factory_resource.r_id = resource.r_id")
    List<FactoryResEntity> queryAll();
}
