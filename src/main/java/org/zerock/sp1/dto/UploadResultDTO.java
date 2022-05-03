package org.zerock.sp1.dto;

import lombok.*;


@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
  
  private String orgFileName;
  private String filePath;
  private String uuid;
  private boolean isImage;
  
  public String getLink(){
    return filePath + "/" + uuid + "_" +orgFileName;
  }
  
  public String getThumbnail(){
    return filePath + "/s_" + uuid + "_" +orgFileName;
  }
  
}
