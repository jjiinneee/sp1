package org.zerock.sp1.domain;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class Smember {
  
  private String mid;
  private String mpw;
  private String name;
  private String nickname;
  private LocalDateTime regdate;
  private LocalDateTime updateDate;
  
  @Builder.Default
  private List<Sauth> sauthList = new ArrayList<>();
  
  

}
