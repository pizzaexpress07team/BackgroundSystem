package com.project.PizzaExpress.dao;

import com.project.PizzaExpress.entity.UserEntity;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserDAO {

    @Select("select password from user where username = #{name}")
    List<String> query(String name);

    @Select("select uid from user where username = #{name}")
    List<String> queryUID(String name);

    @Select("select * from user where uid = #{uid}")//uid, username, password, addr, is_admin, phone, sina, qq, create_time
//    @Results({
//            @Result(property = "uid", column = "uid"),
//            @Result(property = "username", column = "username"),
//            @Result(property = "password", column = "password"),
//            @Result(property = "addr", column = "addr"),
//            @Result(property = "is_admin", column = "is_admin"),
//            @Result(property = "phone", column = "phone"),
//            @Result(property = "sina", column = "sina"),
//            @Result(property = "qq", column = "qq"),
//            @Result(property = "create_time", column = "create_time")
//    })
    List<UserEntity> queryUserInfo(String uid);

    @Insert("insert into user(uid,username,password,addr,is_admin,phone,sina,qq,create_time)"+
            "values(#{uid},#{username},#{password},#{addr},#{is_admin},#{phone},#{sina},#{qq},#{create_time})")
    void insert(UserEntity ue);

    @Update("update user set username = #{username},password = #{password},addr = #{addr},is_admin = #{is_admin}," +
             "phone = #{phone},sina = #{sina},qq = #{qq} where uid = #{uid}")
    int update(UserEntity ue);

    @Update(("update user set addr = #{addr} where uid = #{uid}"))
    int updateAddr(String uid, String addr);

    @Select("select * from user where is_admin = 0 limit ${startIndex},${pageSize}")
    List<UserEntity> queryAllUserInfo(@Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);

    @Select("select uid from user where username = #{username}")
    List<String> queryUidByUserName(@Param("username")String username);

    @Select("select count(*) from user")
    String queryUserSize();

    @Select("select * from user where username = #{username}")
    List<UserEntity> queryByUserName(String username);

    @Select("select * from user where username like #{username}")
    List<UserEntity> queryByUserNameLike(String username);

    @Delete("delete from user where uid = #{uid}")
    void deleteInfoByUid(String uid);
}
