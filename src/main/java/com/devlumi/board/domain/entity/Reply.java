package com.devlumi.board.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")
public class Reply extends BaseEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rno;
  private String text;
  private String replyer;

  @ManyToOne(fetch =  FetchType.LAZY)
  private Board board;
}
