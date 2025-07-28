package com.devlumi.board.domain.projection.dto;

import com.devlumi.board.domain.entity.Board;
import com.devlumi.board.domain.entity.Member;


public record BoardWithWriterDTORecord(Board board, Member member){
}
