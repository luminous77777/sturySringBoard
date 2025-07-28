package com.devlumi.board.domain.projection.dto;

import com.devlumi.board.domain.entity.Board;

import java.io.Writer;

public interface BoardWithReplyCountDTO {
  Board getBoard();
  Writer getWriter();
  Integer getReplyCount();
}
