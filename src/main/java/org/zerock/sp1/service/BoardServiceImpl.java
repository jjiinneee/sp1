package org.zerock.sp1.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.sp1.domain.Board;
import org.zerock.sp1.dto.BoardDTO;
import org.zerock.sp1.dto.ListDTO;
import org.zerock.sp1.dto.ListResponseDTO;
import org.zerock.sp1.mapper.BoardMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;
    private final ModelMapper modelMapper;

    @Override
    public ListResponseDTO<BoardDTO> getList(ListDTO listDTO){
        List<Board> boardList = boardMapper.selectList(listDTO);

        List<BoardDTO> dtoList =
                boardList.stream().map(board -> modelMapper.map(board,BoardDTO.class)).collect(Collectors.toList());


        return ListResponseDTO.<BoardDTO>builder().dtoList(dtoList)
                 .total(boardMapper.getTotal(listDTO))
                .build();
    }

    
    @Override
    public BoardDTO getOne(int bno){
        Board board = boardMapper.selectOne(bno);
        
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        
        return boardDTO;
    }
    
    @Override
    public void remove(int bno){
        boardMapper.delete(bno);
    }
    
    @Override
    public void update(BoardDTO boardDTO){
        boardMapper.update(Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .build());
    }
}
