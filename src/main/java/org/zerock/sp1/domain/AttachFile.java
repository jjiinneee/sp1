package org.zerock.sp1.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AttachFile {
  private String uuid;
  private Integer bno;
  private String filename;
  private String savepath;
  private Boolean img;
}
