package com.devlumi.board.repository;

import com.devlumi.board.entity.Board;
import com.devlumi.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

  void deleteByBoard_Bno(Long bno);
}
