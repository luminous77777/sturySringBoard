package com.devlumi.board.service;

import com.devlumi.board.domain.dto.ReplyDTO;
import com.devlumi.board.domain.entity.Board;
import com.devlumi.board.domain.entity.Reply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest
public class ReplyServiceTest {
  @Autowired
  private ReplyService replyService;

  @Test
  @DisplayName("객체 취득 테스트")
  public void testExist() {
    log.info(replyService);
  }

  @Test
  @DisplayName("리뷰 작성")
  public void testRegister() {
    Long result = replyService.register(replyService.toDTO(Reply.builder().replyer("작성자").text("내용").board(Board.builder().bno(1L).build()).build()));
    Assertions.assertNotNull(result);
  }

  @Test
  @DisplayName("리뷰 수정")
  public void testModify() {
    replyService.modify(replyService.toDTO(Reply.builder().rno(1L).replyer("윈터").board(Board.builder().bno(76L).build()).text("수정된 내용").build()));
  }

  @Test
  @DisplayName("리뷰 삭제")
  public void testDelete() {
    replyService.delete(2L);
  }

  @Test
  @DisplayName("리뷰 단일 조회")
  public void testGet() {
      ReplyDTO replyDTO = replyService.get(1000L);
      log.info(replyDTO);
  }

  @Test
  @DisplayName("리뷰 목록 조회")
  public void testGetList() {
    List<ReplyDTO> replyDTOList = replyService.getList(200L);
    replyDTOList.forEach(log::info);
  }
}
