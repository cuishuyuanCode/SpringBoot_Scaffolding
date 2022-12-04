package com.csy.demo.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface DemoMapper {


    @Select("select id from demo where id = #{id}")
    String selectById(@Param("id") String id);
}
