package com.devlumi.board.projection.dto;

import com.devlumi.board.entity.Board;
import com.devlumi.board.entity.Member;

public record BoardWithReplyCount(Board board, Member member, Long replyCount) {
}
