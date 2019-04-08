package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.OrderEntity;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface OrderDAO {

    @Insert("insert into order(o_id, u_id, o_create_time, o_pay_time, delivery_state, " +
                "f_id, d_id, detail, total_price, o_pay_state, o_delivery_addr, pay_id) "+
            "values(#{o_id}, #{u_id}, #{o_create_time}, #{o_pay_time}, #{delivery_state}, " +
                "#{f_id}, #{d_id}, #{detail}, #{total_price}, #{o_pay_state}, #{o_delivery_addr}, #{pay_id})")
    void insert(OrderEntity oe);

    @Delete("delete from order where o_id = #{o_id}")
    void delete(String o_id);

    @Update("update order set o_pay_time = #{o_pay_time} where o_id = #{o_id}")
    int updatePaytime(String o_id, Timestamp pay_time);

    @Update("update order set delivery_state = #{delivery_state} where o_id = #{o_id}")
    int updateDeliveryState(String o_id, int state);

    @Update("update order set f_id = #{f_id} where o_id = #{o_id}")
    int updateFid(String o_id, String f_id);

    @Update("update order set d_id = #{d_id} where o_id = #{o_id}")
    int updateDid(String o_id, String d_id);

    @Update("update order set detail = #{detail} where o_id = #{o_id}")
    int updateDetail(String o_id, String detail);

    @Update("update order set total_price = #{total_price} where o_id = #{o_id}")
    int updateTotalPrice(String o_id, BigDecimal total_price);

    @Update("update order set o_pay_state = #{o_pay_state} where o_id = #{o_id}")
    int updatePayState(String o_id, String pay_state);

    @Update("update order set o_delivery_addr = #{o_delivery_addr} where o_id = #{o_id}")
    int updateDeliveryAddr(String o_id, String delivery_addr);

    @Update("update order set pay_id = #{pay_id} where o_id = #{o_id}")
    int updatePayId(String o_id, String pay_id);

    @Select("select * from order where o_id = #{o_id}")
    List<OrderEntity> query(String o_id);

    @Select("select * from order where u_id = #{u_id}")
//    @Results({
//            @Result(property = "p_type", column = "p_type"),
//            @Result(property = "p_id", column = "p_id"),
//            @Result(property = "p_name", column = "p_name"),
//            @Result(property = "price", column = "price"),
//            @Result(property = "is_empty", column = "is_empty"),
//            @Result(property = "p_picture", column = "p_picture"),
//            @Result(property = "f_id", column = "f_id"),
//            @Result(property = "p_size", column = "p_size")
//    })
    List<OrderEntity> queryOrders(String u_id);
}
