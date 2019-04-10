package com.project.PizzaExpress.dao;


import com.project.PizzaExpress.entity.DeliverymanEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeliverymanDAO {

    @Insert("insert into deliveryman(d_id, d_name, d_phone, f_id, uid) " +
            "values(#{d_id}, #{d_name}, #{d_phone}, #{f_id}, #{uid})")
    void insert(DeliverymanEntity de);

    @Delete("delete from deliveryman where d_id = #{d_id}")
    void delete(String d_id);

    @Update("update deliveryman set d_name = #{d_name}, d_phone = #{d_phone}, f_id = #{f_id} where d_id = #{d_id}")
    int update(DeliverymanEntity de);

    @Select("select * from deliveryman where d_id = #{d_id}")
    List<DeliverymanEntity> query(String d_id);

    @Select("select * from deliveryman")
    List<DeliverymanEntity> queryAll();

    @Select("select * from deliveryman where f_id = #{f_id}")
    List<DeliverymanEntity> queryDeliverymans(String f_id);

}
