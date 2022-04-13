package org.zerock.sp1.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.sp1.domain.Board;

import java.util.List;

public interface BoardMapper {

    void insert(Board board);

    List<Board> selectList(@Param("skip") int skip, @Param("size")int size);

    void delete(Integer bno);

    Board selectOne(Integer bno);

    void update(Board board);
}
