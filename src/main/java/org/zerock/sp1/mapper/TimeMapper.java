package org.zerock.sp1.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


public interface TimeMapper {
//
//    @Select("select now()")
    String getTime();
}
