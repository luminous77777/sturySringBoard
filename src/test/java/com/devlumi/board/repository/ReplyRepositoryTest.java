package com.devlumi.board.repository;

import com.devlumi.board.domain.entity.Board;
import com.devlumi.board.domain.entity.Reply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


@Log4j2
@SpringBootTest
public class ReplyRepositoryTest {

  @Autowired
  private ReplyRepository repository;
  @Autowired
  private BoardRepository boardRepository;

  @Test
  public void testExitst() {
    Assertions.assertNotNull(repository);}

  @Test
  public void insertReplies() {
    List<Long> bnos = boardRepository.findAll().stream().map(Board::getBno).toList();

    IntStream.rangeClosed(0, 300).forEach(i->{
      long bno = bnos.get(new Random().nextInt(bnos.size()));
      Board board = Board.builder().bno(bno).build();
      Reply reply = Reply.builder()
              .board(board)
              .text("reply"+i)
              .replyer("guest")
              .build();

      repository.save(reply);
    });
  }

  @Test
  public void testRead(){
    Reply reply = repository.findById(1L).orElse(null);
//    log.info(reply);
//    log.info(reply.getBoard());
//    log.info(reply.getBoard().getWriter());

  }

  @Test
  public void testFindByBoard_Bno(){
    repository.findByBoard_BnoOrderByRno(2L).forEach(log::info);
  }

  @Test
  public void testFindByBoard(){
    repository.findByBoard(Board.builder().bno(2L).build()).forEach(log::info);
  }
}
