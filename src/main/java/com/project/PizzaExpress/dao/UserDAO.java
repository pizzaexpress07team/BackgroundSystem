package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserDAO {

    @Select("select password from user where username = #{name}")
    List<String> query(String name);

    @Select("select uid, username, addr, is_admin, phone, sina, qq, create_time from user where username = #{username}")
    @Results({
            @Result(property = "uid", column = "uid"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "addr", column = "addr"),
            @Result(property = "is_admin", column = "is_admin"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "sina", column = "sina"),
            @Result(property = "qq", column = "qq"),
            @Result(property = "create_time", column = "create_time")
    })
    List<UserEntity> queryUserInfo(String uid);

    @Insert("insert into user(uid,username,password,addr,is_admin,phone,sina,qq,create_time)"+
            "values(#{uid},#{username},#{password},#{addr},#{is_admin},#{phone},#{sina},#{qq},#{create_time})")
    void insert(UserEntity ue);

    @Update("update user set username = #{name},password = #{password},addr = #{addr},is_admin = #{is_admin}," +
             "phone = #{phone},sina = #{sina},qq = #{qq} where uid = #{uid}")
    int update(String uid, String password, String addr, int is_admin, String phone, String sina, String qq);

}
