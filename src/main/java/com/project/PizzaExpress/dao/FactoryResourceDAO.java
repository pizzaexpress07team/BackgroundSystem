package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.FactoryResourceEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FactoryResourceDAO {
    @Insert("insert into factory_resource(f_id,r_id,r_num) " +
            "values(#{f_id},#{r_id},#{r_num})")
    void insert(FactoryResourceEntity fre);

    @Update("update factory_resource set r_num = #{r_num} where f_id = #{f_id} and r_id = #{r_id}")
    int updateResourceNum(@Param("f_id") String f_id, @Param("r_id") String r_id, @Param("r_num") int r_num);

    @Select("select * from factory_resource where f_id = #{f_id}")
    List<FactoryResourceEntity> queryByFactory(@Param("f_id") String f_id);

    @Select("SELECT * FROM factory_resource where f_id = #{f_id} and r_id = #{r_id}")
    List<FactoryResourceEntity> queryByFidAndRid(@Param("f_id")String f_id, @Param("r_id")String r_id);
}
