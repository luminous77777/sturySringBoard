package com.devlumi.board.service;

import com.devlumi.board.dto.BoardDTO;
import com.devlumi.board.dto.PageRequestDTO;
import com.devlumi.board.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class BoardServiceTest {

  @Autowired
  private BoardService boardService;

  @Test
  public void testRegister(){
    BoardDTO dto = BoardDTO.builder()
            .title("test title")
            .content("test content")
            .writerEmail("user55@gmail.com")
            .build();

    Long bno = boardService.register(dto);
    log.info(bno);
  }

  @Test
  public void testList(){
    boardService.getList(PageRequestDTO.builder().page(1).size(10).build()).getList().forEach(log::info);
  }

  @Test
  public void testGet(){
    log.info(boardService.get(1L).getTitle());
  }
}
