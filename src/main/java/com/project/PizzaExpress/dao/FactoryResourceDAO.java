package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.FactoryResourceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FactoryResourceDAO {

    @Update("update factory_resource set r_num = #{r_num} where f_id = #{f_id} and r_id = #{r_id}")
    int updateResourceNum(@Param("f_id") String f_id, @Param("r_id") String r_id, @Param("r_num") int r_num);

    @Select("select * from factory_resource where f_id = #{f_id}")
    List<FactoryResourceEntity> queryByFactory(@Param("f_id") String f_id);
}
