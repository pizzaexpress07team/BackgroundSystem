package com.project.PizzaExpress.dao;


import com.project.PizzaExpress.entity.DeliManWithOrderEntity;
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
    List<DeliverymanEntity> queryDeliverymansByFid(String f_id);

    @Update("update deliveryman set lng = #{lng}, lat = #{lat} where d_id = #{d_id}")
    int updateLocation(String d_id, double lng, double lat);

    @Select("select * from deliveryman limit ${startIndex},${pageSize}")
    List<DeliverymanEntity> queryAllByPage(@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);

    @Select("select * from deliveryman where d_name like #{d_name}")
    List<DeliverymanEntity> queryLike(String d_name);

    @Select("SELECT * FROM deliveryman JOIN `order` ON deliveryman.d_id = order.d_id limit ${startIndex},${pageSize}")
    List<DeliManWithOrderEntity> queryDelimanOrderByPage(@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);

    @Select("SELECT COUNT(*) FROM deliveryman JOIN `order` ON deliveryman.d_id = order.d_id")
    String queryDelimanOrderSize();

    @Select("SELECT COUNT(*) FROM deliveryman")
    String queryDelimanSize();
}
