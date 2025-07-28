package com.devlumi.board.repository;

import com.devlumi.board.domain.projection.dto.*;
import com.devlumi.board.domain.entity.Board;
import com.devlumi.board.domain.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class BoardRepositoryTest {

  @Autowired
  private BoardRepository repository;

  @Test
  public void testExitst() {
    Assertions.assertNotNull(repository);}

  @Test
  public void insertBoards(){
    IntStream.range(0, 100).forEach(i -> {
      Member member = Member.builder().email("user" + i + "@gmail.com").build();

      Board board = Board.builder()
              .title("title" + i)
              .content("content" + i)
              .writer(member)
              .build();

      repository.save(board);
    });
  }

  @Test
  @Transactional(readOnly = true)
  public void testRead() {
    Board board = repository.findById(1L).orElse(null);
    log.info(board);
    log.info(board.getWriter());
  }

  @Test
  public void testReadWithWriter(){
//    Arrays.stream((Object[]) repository.getBoardWithWriter(1L)).forEach(log::info);
    BoardWithWriterDTO dto = repository.getBoardWithWriter(1L);
    log.info(dto.getBoard());
    log.info(dto.getMember());
  }

  @Test
  public void testReadWithWriter2(){
    BoardWithWriterDTORecord dto = repository.getBoardWithWriter2(1L);
    log.info(dto.board());
    log.info(dto.member());
  }

  @Test
  public void testReadWithWriter3(){
    BoardWithWriterDTOClass dto = repository.getBoardWithWriter3(1L);
    log.info(dto.getBoard());
  }

  @Test
  public void testGetBoardWithReply(){

    List<Object[]> result = repository.getBoardWithReply(1L);

//    for (Object[] arr : result) {
//      log.info(Arrays.toString(arr));
//    }
    result.stream().forEach(o -> log.info(Arrays.toString(o)));
    result.stream().forEach(o -> log.info("{} {}", o[0], o[1]));
  }

  @Test
  public void testGetBoardWithReply2(){
    List<BoardWithReplyDTO> result = repository.getBoardWithReply2(1L);
    result.stream().forEach(log::info);
  }

  @Test
  public void testBoardWithReplyCount(){
    Pageable pageable = PageRequest.of(0,10, Sort.by("bno"));

    Page<Object[]> result = repository.getBoardWithReplyCount(pageable);

//    result.get().forEach(o -> log.info(Arrays.toString(o)));
    result.get().forEach(o -> log.info("{} {} {}",  o[0], o[1], o[2]));
  }

  @Test
  public void testBoardWithReplyCount2(){
    Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "bno"));
//PageRequest.of
    Page<BoardWithReplyCountDTO> result = repository.getBoardWithReplyCount2(pageable);

//    result.get().forEach(log::info);

  }

  @Test
  public void testBoardWithReplyCount3(){
    Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "bno"));
    Page<BoardWithReplyCount> page = repository.getBoardWithReplyCount3(pageable);

    page.get().forEach(log::info);
  }

  @Test
  public void testSearch1(){  //qclass 확인  -> queryDSL을 사용하기 위한 최소 조건!!
    repository.search1();
  }

  @Test
  public void testSearchPage(){

    Page<BoardWithReplyCount> bwrc = repository.searchPage("tcw","title1", PageRequest.of(1, 5, Sort.by(Sort.Direction.DESC, "bno").and(Sort.by(Sort.Direction.ASC, "title"))));

    log.info(bwrc.getTotalPages());
    log.info(bwrc.getTotalElements());
    bwrc.getContent().forEach(log::info);
  }


}
