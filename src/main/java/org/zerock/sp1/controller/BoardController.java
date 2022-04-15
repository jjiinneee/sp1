package org.zerock.sp1.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.sp1.dto.BoardDTO;
import org.zerock.sp1.dto.ListDTO;
import org.zerock.sp1.dto.ListResponseDTO;
import org.zerock.sp1.dto.PageMaker;
import org.zerock.sp1.service.BoardService;

@Log4j2
@Controller
@RequestMapping("/board")
public class BoardController {

    private  BoardService service;
    @GetMapping("/")
    public String basic(){
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void list(ListDTO listDTO, Model model){
//    public void list(@ModelAttribute(name = "cri") ListDTO listDTO){
       //     @RequestParam(name="page" , defaultValue = "1" , required = true) int page){

        ListResponseDTO<BoardDTO>  responseDTO = service.getList(listDTO);
        log.info("board list잘찍혔다.");
        log.info(listDTO);

        model.addAttribute("dtoList",responseDTO.getDtoList());
      //  model.addAttribute("total",responseDTO.getTotal());
        //log.info(page);
        int total = responseDTO.getTotal();
//        model.addAttribute("pageMaker", new PageMaker(,total));
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
