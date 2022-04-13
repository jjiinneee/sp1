package org.zerock.sp1.store;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@ToString
@Service
@RequiredArgsConstructor
public class Restaurant {

    private final Chef chef;

    //생성자 주입
    //lombok은 노필요
    //주입이 필요하면 final 선언 => 자동주입
//    public Restaurant(Chef chef) {
//        this.chef = chef;
//    }
}
