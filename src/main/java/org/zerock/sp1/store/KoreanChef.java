package org.zerock.sp1.store;

import org.springframework.stereotype.Repository;

@Repository
public class KoreanChef implements Chef{
    @Override
    public String cook(){
        return "불고기";
    }

    @Override
    public String makeCook(){
        return "얌야미";
    }
}
