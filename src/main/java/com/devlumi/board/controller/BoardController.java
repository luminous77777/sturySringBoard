package com.devlumi.board.controller;

import com.devlumi.board.domain.dto.BoardDTO;
import com.devlumi.board.domain.dto.PageRequestDTO;
import com.devlumi.board.service.BoardService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
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

  @GetMapping("read")
  public void read(@ModelAttribute("requestDto") PageRequestDTO dto, Model model, Long bno){
    log.info("readpage.....");
    model.addAttribute("dto", boardService.get(bno));
  }

  @GetMapping("modify")
  public void modify(@ModelAttribute("requestDto") PageRequestDTO pageDto, Long bno, Model model) {
    model.addAttribute("dto", boardService.get(bno));
  }

  @PostMapping("modify")
  public String modify(PageRequestDTO dto,  BoardDTO boardDTO, RedirectAttributes rttr) {
    boardService.modify(boardDTO);
    rttr.addAttribute("bno", boardDTO.getBno());
    rttr.addAttribute("page", dto.getPage());
    rttr.addAttribute("size", dto.getSize());
    rttr.addAttribute("type", dto.getType());
    rttr.addAttribute("key", dto.getKeyword());
    return "redirect:read";
  }

  @PostMapping ("remove")
  public String remove(PageRequestDTO dto, Long bno, RedirectAttributes rttr){
    boardService.remove(bno);

    rttr.addFlashAttribute("msg", bno);
    rttr.addAttribute("page", 1);
    rttr.addAttribute("size", dto.getSize());
    return "redirect:list";
  }


}
