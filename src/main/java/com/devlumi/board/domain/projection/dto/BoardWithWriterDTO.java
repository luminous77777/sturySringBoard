package com.devlumi.board.domain.projection.dto;

import com.devlumi.board.domain.entity.Board;
import com.devlumi.board.domain.entity.Member;

public interface BoardWithWriterDTO {
  Board getBoard();
  Member getMember();
}
