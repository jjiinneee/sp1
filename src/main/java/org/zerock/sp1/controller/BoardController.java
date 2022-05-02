package org.zerock.sp1.controller;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;
    
    @GetMapping("/read/{bno}")
    public String read(@PathVariable("bno") Integer bno, ListDTO listDTO, Model model){
        log.info("Readd------");
        log.info(bno);
        log.info(listDTO);
        
        //사용자가 정의한 함수 model로 전송쓰
        model.addAttribute("dto", service.getOne(bno));
        
        return "/board/read2";
    }
    
    
    @GetMapping({"modify/{bno}"})
    public String modifyGET(@PathVariable("bno") Integer bno, ListDTO listDTO, Model model){
        model.addAttribute("dto", service.getOne(bno));
        
        return "/board/modify";
    }
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
        model.addAttribute("total",responseDTO.getTotal());
        //log.info(page);
        int total = responseDTO.getTotal();
        model.addAttribute("pageMaker", new PageMaker(listDTO.getPage(), total));
    }


    @GetMapping("/register")
    public void reigiterGET(){

    }

    @PostMapping("/register")
    public String registerPOST(BoardDTO boardDTO , RedirectAttributes rttr){

        log.info("-------post-------");
        log.info(boardDTO);

        //한번만 읽고 사라짐(한번만 잠깐 쓰고 다시는 안쓰고싶을때)
        rttr.addFlashAttribute("result", 123);

        //값 추가하고 싶을때 사용 (listponse Header locationㅇㅔ 내역나옴)
        // redirect 할때 데이터를 뒤에 붙이고 싶으면 addAttribute
      //  rttr.addAttribute("num",321);
        return "redirect:/board/list";
//        return  "redirect:/board/list?result=123";

    }
    
    
    @GetMapping({"/remove/{bno"})
    public String getNotSupported(){
        return "redirect:/board/list";
    }
    @PostMapping("/remove/{bno}")
    public String removePost(
            @PathVariable ("bno") Integer bno,
            RedirectAttributes rttr){
    
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        
        service.remove(bno);
        rttr.addFlashAttribute("result", "removed");
        return "redirect:/board/list";
    }
    
    @PostMapping("/modify/{bno}")
    public String removePost(@PathVariable("bno") Integer bno, BoardDTO boardDTO,  ListDTO listDTO,  RedirectAttributes rttr){
        service.update(boardDTO);
        
        rttr.addFlashAttribute("result", "modified");
        
        return "redirect:/board/read2/"+bno + listDTO.getLink();
    }
}
