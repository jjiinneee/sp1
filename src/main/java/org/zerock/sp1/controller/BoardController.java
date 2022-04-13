package org.zerock.sp1.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.sp1.dto.BoardDTO;

@Log4j2
@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/")
    public String basic(){
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void list(){
        log.info("board list잘찍혔다.");
    }


    @GetMapping("/register")
    public void reigiterGET(){

    }

    @PostMapping("/register")
    public String registerPOST(BoardDTO boardDTO , RedirectAttributes rttr){

        log.info("-------post-------");
        log.info(boardDTO);

        //한번만 읽고 사라짐
        rttr.addFlashAttribute("result", 123);
        //값 추가하고 싶을때 사용
        rttr.addAttribute("num",1234);
        return "redirect:/board/list";
//        return  "redirect:/board/list?result=123";

    }
}
