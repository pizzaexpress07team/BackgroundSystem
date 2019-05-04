package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.OrderEntity;
import com.project.PizzaExpress.entity.OrderFactoryEntity;
import org.apache.ibatis.annotations.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface OrderDAO {

    @Insert("insert into `order`(o_id, u_id, o_create_time, o_pay_time, delivery_state, " +
                "f_id, d_id, detail, total_price, o_pay_state, o_delivery_addr, pay_id) "+
            "values(#{o_id}, #{u_id}, #{o_create_time}, #{o_pay_time}, #{delivery_state}, " +
                "#{f_id}, #{d_id}, #{detail}, #{total_price}, #{o_pay_state}, #{o_delivery_addr}, #{pay_id})")
    void insert(OrderEntity oe);

    @Delete("delete from `order` where o_id = #{o_id}")
    void delete(String o_id);

    @Update("update `order` set o_pay_time = #{pay_time} where o_id = #{o_id}")
    int updatePaytime(@Param("o_id")String o_id, @Param("pay_time")Timestamp pay_time);

    @Update("update `order` set delivery_state = #{delivery_state} where o_id = #{o_id}")
    int updateDeliveryState(@Param("o_id")String o_id, @Param("delivery_state")int delivery_state);

    @Update("update `order` set f_id = #{f_id} where o_id = #{o_id}")
    int updateFid(@Param("o_id")String o_id, @Param("f_id")String f_id);

    @Update("update `order` set d_id = #{d_id} where o_id = #{o_id}")
    int updateDid(@Param("o_id")String o_id, @Param("d_id")String d_id);

    @Update("update `order` set detail = #{detail} where o_id = #{o_id}")
    int updateDetail(@Param("o_id")String o_id, String detail);

    @Update("update `order` set total_price = #{total_price} where o_id = #{o_id}")
    int updateTotalPrice(@Param("o_id")String o_id, BigDecimal total_price);

    @Update("update `order` set o_pay_state = #{o_pay_state} where o_id = #{o_id}")
    int updatePayState(@Param("o_id")String o_id, @Param("o_pay_state")int o_pay_state);

    @Update("update `order` set o_delivery_addr = #{o_delivery_addr} where o_id = #{o_id}")
    int updateDeliveryAddr(String o_id, String o_delivery_addr);

    @Update("update `order` set pay_id = #{pay_id} where o_id = #{o_id}")
    int updatePayId(String o_id, String pay_id);

    @Select("select * from `order` where o_id = #{o_id}")
    List<OrderEntity> query(String o_id);

    @Select("select * from `order` where o_id like #{o_id}")
    List<OrderEntity> queryLike(@Param("o_id")String o_id);

    @Select("select * from `order` where u_id = #{u_id} order by o_create_time desc")
    List<OrderEntity> queryOrders(@Param("u_id") String u_id);

    /*
        insertRecord, deleteRecord, updateRecord, queryRecord for the operations of table "order_factory"
     */
    @Insert("insert into order_factory(o_id, f_list) values(#{o_id}, #{f_list})")
    void insertRecord(OrderFactoryEntity ofe);

    @Delete("delete from order_factory where o_id = #{o_id}")
    void deleteRecord(String o_id);

    @Update("update order_factory set f_list = #{f_list}, where o_id = #{o_id}")
    int updateRecord(OrderFactoryEntity ofe);

    @Select("select * from order_factory where o_id = #{o_id}")
    List<OrderFactoryEntity> queryRecord(String o_id);

    @Select("select * from `order` limit ${startIndex},${pageSize}")
    List<OrderEntity> queryAll(@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);

    @Select("SELECT * FROM `order` ORDER BY o_create_time DESC limit ${startIndex},${pageSize}")
    List<OrderEntity> queryAllByTime(@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);

    @Select("SELECT COUNT(*) FROM `order`")
    String queryOrderSize();

    @Select("select * from `order` where u_id = #{u_id}")
    List<OrderEntity> queryOrderByUid(@Param("u_id")String u_id);

    @Select("select delivery_state from `order` where o_id = #{o_id}")
    List<Integer> queryDeliStateByOid(@Param("o_id")String o_id);
}
