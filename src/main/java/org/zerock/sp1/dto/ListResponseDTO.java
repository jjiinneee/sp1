package org.zerock.sp1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//builder 로 뺀이유 total 안쓸수도 있으니까~
public class ListResponseDTO <E>{

    private List<E> dtoList;

    private int total;

}
