package org.zerock.sp1.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


public interface TimeMapper {
//
//    @Select("select now()")
    String getTime();
    
    @Insert("insert into tbl_a(text) values (#{text})")
    void insertA(String text);
    @Insert("insert into tbl_b(text) values (#{text})")
    void insertB(String text);
}
