package org.zerock.sp1.service;

import org.zerock.sp1.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {
  
  List<ReplyDTO> getListOfBoard(Integer bno);
}
