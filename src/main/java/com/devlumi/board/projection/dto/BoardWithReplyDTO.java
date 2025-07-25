package com.devlumi.board.projection.dto;

import com.devlumi.board.entity.Board;
import com.devlumi.board.entity.Reply;

public interface BoardWithReplyDTO {
  Board getBoard();
  Reply getReply();
}
