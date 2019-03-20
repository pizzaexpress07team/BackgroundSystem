package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface UserDAO {

    @Select("select password from user where username = #{name}")
    List<String> query(String name);

    @Insert("insert into user(uid,username,password,addr,is_admin,phone,sina,qq,create_time)"+
            "values(#{uid},#{username},#{password},#{addr},#{is_admin},#{phone},#{sina},#{qq},#{create_time})")
    boolean insert(UserEntity ue);

    @Update("update user set username = #{name},password = #{password},addr = #{addr},is_admin = #{is_admin}," +
             "phone = #{phone},sina = #{sina},qq = #{qq},create_time = #{create_time} where uid = #{uid}")
    boolean update(String uid, String password, String addr, int is_admin, String phone, String sina, String qq, Timestamp create_time);

}
