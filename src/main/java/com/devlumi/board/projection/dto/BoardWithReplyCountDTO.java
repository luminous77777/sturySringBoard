package com.devlumi.board.projection.dto;

import com.devlumi.board.entity.Board;

import java.io.Writer;

public interface BoardWithReplyCountDTO {
  Board getBoard();
  Writer getWriter();
  Integer getReplyCount();
}
