package org.zerock.sp1.domain;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    private Integer bno;
    private String title;
    private String content;
    private String writer;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
