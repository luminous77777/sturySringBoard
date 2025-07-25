package com.devlumi.board.projection.dto;

import com.devlumi.board.entity.Board;
import com.devlumi.board.entity.Member;


public record BoardWithWriterDTORecord(Board board, Member member){
}
