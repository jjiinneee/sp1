package org.zerock.sp1.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@ToString
@Getter
public class ListDTO {

  
    private int page;

    private int size;

    //검색 타입
    private String type;

    private String keyword;

    public ListDTO(){
        this.page = 1;
        this.size = 10;
    }

    public String[] getTypes(){
        if(type == null || type.trim().length() == 0){
            return new String[]{};
        }
        return type.split("");
    }


    public String getKeyword(){
        return keyword == null || keyword.trim().length() == 0 ? null : keyword.trim();
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setPage(int page) {
        this.page = page <= 0 ? 1 :page;
    }

    public void setSize(int size) {
      //  this.size = size <= 10 ? 10 :size >=1000 ?10 :size;
        this.size = size <= 10 ? 10 :size;
    }

    public int getSkip(){
        return (this.page -1) * size;
    }

    public String getLink(){
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        builder.queryParam("page",getPage())
                .queryParam("size",getSize());


        return builder.toString();
    }
}
