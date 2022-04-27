package org.zerock.sp1.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
  
  private Integer rno;
  private Integer bno;
  private String replyText;
  private String replier;
  private LocalDateTime regDate;
  private LocalDateTime updateDate;
}
