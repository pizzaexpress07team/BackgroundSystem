package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.FactoryResEntity;
import com.project.PizzaExpress.entity.ResourceEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResourceDAO {
    @Insert("insert into resource(r_id,r_name,r_company,r_person,r_type) " +
            "values(#{r_id},#{r_name},#{r_company},#{r_person},#{r_type})")
    void insert(ResourceEntity re);

    @Select("SELECT r_id FROM resource where r_name = #{r_name}")
    String queryRidByRname(@Param("r_name")String r_name);

    @Select("select * from resource where r_id = #{r_id}")
    List<ResourceEntity> queryByRid(@Param("r_id")String r_id);
}
