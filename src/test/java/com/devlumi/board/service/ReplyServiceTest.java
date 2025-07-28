package com.devlumi.board.service;

import com.devlumi.board.domain.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
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
  public void testExist(){
    log.info("replyService");
  }

  @Test
  public void testRegister() {
    ReplyDTO dto = ReplyDTO.builder()
            .text("테스트 댓글입니다.")
            .replyer("tester")
            .bno(1L) // 실제 존재하는 게시글 번호로 대체
            .build();

    Long rno = replyService.register(dto);
    log.info("생성된 댓글 번호: {}", rno);
    Assertions.assertNotNull(rno);
  }

  @Test
  public void testGetList() {
    Long bno = 1L; // 실제 존재하는 게시글 번호로 대체
    List<ReplyDTO> replies = replyService.getList(bno);
    Assertions.assertNotNull(replies);
    log.info("댓글 목록: {}", replies);
  }

  @Test
  public void testModify() {
    Long rno = 904L; // 수정 대상 댓글 번호

    ReplyDTO existing = replyService.get(rno);
    Assertions.assertNotNull(existing);
    existing.setText("수정된 댓글 내용입니다.");
    replyService.modify(existing);

    ReplyDTO modified = replyService.get(rno);
    Assertions.assertEquals("수정된 댓글 내용입니다.", modified.getText());
  }



  @Test
  public void testGet() {
    Long rno = 904L; // 실제 존재하는 댓글 번호로 대체

    ReplyDTO dto = replyService.get(rno);
    Assertions.assertNotNull(dto);
    log.info("조회된 댓글: {}", dto);
  }


  @Test
  public void testRemove() {
    Long rno = 904L;

    replyService.remove(rno);
//    ReplyDTO deleted = replyService.get(rno);
//    Assertions.assertNull(deleted);

  }
}
