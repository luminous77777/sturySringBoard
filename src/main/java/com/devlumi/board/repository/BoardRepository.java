package com.devlumi.board.repository;

import com.devlumi.board.projection.dto.*;
import com.devlumi.board.entity.Board;
import com.devlumi.board.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {

  @Query("select b board, w as member from Board b left join b.writer w where b.bno =:bno")
  BoardWithWriterDTO getBoardWithWriter(@Param("bno")  Long bno);

  @Query("select b board, w as member from Board b left join b.writer w where b.bno =:bno")
  BoardWithWriterDTORecord getBoardWithWriter2(@Param("bno")  Long bno);

  @Query("select b board, w as member from Board b left join b.writer w where b.bno =:bno")
  BoardWithWriterDTOClass getBoardWithWriter3(@Param("bno")  Long bno);

  @Query("select b board , r reply from Board b left join Reply r on r.board = b where b.bno = :bno")
  List<Object[]> getBoardWithReply(@Param("bno") Long bno);

  @Query("select b board , r reply from Board b left join Reply r on r.board = b where b.bno = :bno")
  List<BoardWithReplyDTO> getBoardWithReply2(@Param("bno") Long bno);

  @Query(value = "SELECT b, w, count(r) FROM Board b LEFT JOIN b.writer w LEFT JOIN Reply r ON r.board = b GROUP BY b",
  countQuery = "SELECT count(b) FROM Board b")
  Page<Object[]> getBoardWithReplyCount(Pageable pageable);

  @Query(value = "SELECT b board, w write, count(r) count FROM Board b LEFT JOIN b.writer w LEFT JOIN Reply r ON r.board = b GROUP BY b",
          countQuery = "SELECT count(b) FROM Board b")
  Page<BoardWithReplyCountDTO> getBoardWithReplyCount2(Pageable pageable);



  @Query(value = "SELECT b board, w write, count(r) replyCount FROM Board b LEFT JOIN b.writer w LEFT JOIN Reply r ON r.board = b GROUP BY b",
          countQuery = "SELECT count(b) FROM Board b")
  Page<BoardWithReplyCount> getBoardWithReplyCount3(Pageable pageable);

  @Query(value = "SELECT b board, w write, count(r) replyCount FROM Board b LEFT JOIN b.writer w LEFT JOIN Reply r ON r.board = b where b.bno = :bno")
  BoardWithReplyCount getBoardByBno(Long bno);
}
