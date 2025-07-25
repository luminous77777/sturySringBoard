package com.devlumi.board.projection.dto;

import com.devlumi.board.entity.Board;
import com.devlumi.board.entity.Reply;

public record BoardWithReplyDTORecord(Board board, Reply reply) {
}
