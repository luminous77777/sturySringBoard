package com.devlumi.board.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyDTO {
  private Long rno;
  private String text;
  private String replyer;
  private Long bno;
  private LocalDateTime regDate,modDate;
}
