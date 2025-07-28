package com.devlumi.board.domain.projection.dto;

import com.devlumi.board.domain.entity.Board;
import com.devlumi.board.domain.entity.Member;

public record BoardWithReplyCount(Board board, Member member, Long replyCount) {
}
