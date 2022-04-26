package org.zerock.sp1.service;

import org.springframework.stereotype.Service;
import org.zerock.sp1.dto.BoardDTO;
import org.zerock.sp1.dto.ListDTO;
import org.zerock.sp1.dto.ListResponseDTO;

import java.util.List;

public interface BoardService {

    ListResponseDTO<BoardDTO> getList(ListDTO listDTO);
    
    BoardDTO getOne(int bno);
    
    void remove(int bno);

    void update(BoardDTO boardDTO);
}
