package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.FactoryResEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ResourceDAO {
    @Select("SELECT * FROM factory_resource JOIN resource ON factory_resource.r_id = resource.r_id")
    List<FactoryResEntity> queryAll();

    @Select("SELECT * FROM factory_resource JOIN resource ON factory_resource.r_id = resource.r_id where f_id like #{f_id}")
    List<FactoryResEntity> queryLike(@Param("f_id")String f_id);

    @Select("SELECT r_id FROM resource where r_name = #{r_name}")
    String queryRidByRname(@Param("r_name")String r_name);
}
