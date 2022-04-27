package org.zerock.sp1.service;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.sp1.domain.Reply;
import org.zerock.sp1.dto.ReplyDTO;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class ReplyServiceTest {
  
  @Autowired
  private ReplyService replyService;
  
  @Test
  public void test(){
    ReplyDTO replyDTO = new ReplyDTO();
    replyDTO.setBno(3);
    replyDTO.setReplyText("댓글 서비스 통한 댓글 추가");
    replyDTO.setReplier("user11");
    
    replyService.register(replyDTO);
  }
}
