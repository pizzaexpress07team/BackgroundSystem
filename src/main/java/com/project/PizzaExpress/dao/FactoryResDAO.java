package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.FactoryResEntity;
import com.project.PizzaExpress.entity.FactoryResourceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FactoryResDAO {

    @Select("SELECT * FROM factory_resource JOIN resource ON factory_resource.r_id = resource.r_id")
    List<FactoryResEntity> queryAll();

    @Select("SELECT * FROM factory_resource JOIN resource ON factory_resource.r_id = resource.r_id where f_id like #{f_id}")
    List<FactoryResEntity> queryLike(@Param("f_id")String f_id);

    @Select("SELECT * FROM factory_resource JOIN resource ON factory_resource.r_id = resource.r_id where f_id = #{f_id}")
    List<FactoryResEntity> query(@Param("f_id")String f_id);

    @Select("SELECT * FROM factory_resource JOIN resource ON factory_resource.r_id = resource.r_id where r_name like #{r_name}")
    List<FactoryResEntity> queryByNameLike(@Param("r_name")String r_name);

    @Select("SELECT * FROM factory_resource JOIN resource ON factory_resource.r_id = resource.r_id where r_name = #{r_name}")
    List<FactoryResEntity> queryByName(@Param("r_name")String r_name);

    @Select("SELECT * FROM factory_resource JOIN resource ON factory_resource.r_id = resource.r_id where f_id = #{f_id} and r_id = #{r_id}")
    List<FactoryResEntity> queryByFidAndRid(@Param("f_id")String f_id, @Param("r_id")String r_id);

}
