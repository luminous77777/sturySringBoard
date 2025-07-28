package com.devlumi.board.domain.projection.dto;

import com.devlumi.board.domain.entity.Board;
import com.devlumi.board.domain.entity.Member;
import lombok.Getter;

@Getter
public class BoardWithWriterDTOClass {  //이것과 레코드가 같다
  private final Board board;
  private final Member member;

  public BoardWithWriterDTOClass(Board board, Member member) {
    this.board = board;
    this.member = member;
  }
}
