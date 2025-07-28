package com.devlumi.board.domain.projection.dto;

import com.devlumi.board.domain.entity.Board;
import com.devlumi.board.domain.entity.Reply;

public record BoardWithReplyDTORecord(Board board, Reply reply) {
}
