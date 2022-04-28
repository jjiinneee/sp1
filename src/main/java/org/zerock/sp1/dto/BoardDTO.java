package org.zerock.sp1.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class BoardDTO {

    private Integer bno;
    private String title;
    private String content;
    private String writer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime regDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updateDate;
    private int replyCount;
}
