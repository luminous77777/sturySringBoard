package com.devlumi.board.projection.dto;

import com.devlumi.board.entity.Board;
import com.devlumi.board.entity.Member;

public interface BoardWithWriterDTO {
  Board getBoard();
  Member getMember();
}
