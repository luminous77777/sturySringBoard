package com.devlumi.board.controller;

import com.devlumi.board.dto.BoardDTO;
import com.devlumi.board.dto.PageRequestDTO;
import com.devlumi.board.service.BoardService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("board")
@Log4j2
@Data
public class BoardController {
  private final BoardService boardService;

  //등록폼
  @GetMapping("register")
  public void register(){
    log.info("register get............");
  }

  //등록 프로세스
  @PostMapping("register")
  public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes){
    log.info("boardDTO ....." + dto);

    Long bno =  boardService.register(dto);

    log.info("BNO:" + bno);

    redirectAttributes.addFlashAttribute("msg", bno);

    return "redirect:/board/list";
  }


  //목록 조회
  @GetMapping("list")
  public void list(@ModelAttribute("requestDto") PageRequestDTO dto, Model model){

    log.info("list ..........." + dto);

    model.addAttribute("dto", boardService.getList(dto));
  }
}
