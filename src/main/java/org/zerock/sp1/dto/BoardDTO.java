package org.zerock.sp1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private String mainImage;
    
    private List<UploadResultDTO> uploads = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime regDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updateDate;
    private int replyCount;
    
    
}
