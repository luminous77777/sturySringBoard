package com.devlumi.board.service;

import com.devlumi.board.domain.dto.BoardDTO;
import com.devlumi.board.domain.dto.PageRequestDTO;
import com.devlumi.board.domain.dto.PageResponseDTO;
import com.devlumi.board.domain.entity.Board;
import com.devlumi.board.domain.entity.Member;
import com.devlumi.board.domain.projection.dto.BoardWithReplyCount;


public interface BoardService {
  Long register(BoardDTO boardDTO);

  PageResponseDTO<BoardDTO, BoardWithReplyCount> getList(PageRequestDTO pageRequestDTO);

  BoardDTO get(Long bno);

  void remove(Long bno);

  void modify(BoardDTO boardDTO);

  //DML(insert, update
  default Board toEntity(BoardDTO dto) {
    return Board.builder()
            .bno(dto.getBno())
            .title(dto.getTitle())
            .content(dto.getContent())
            .writer(Member.builder().email(dto.getWriterEmail()).build())
            .build();
  }
  //select
  default BoardDTO toDTO(Board entity, Member member, Long replyCnt) {
    return BoardDTO.builder()
            .bno(entity.getBno())
            .title(entity.getTitle())
            .content(entity.getContent())
            .regDate(entity.getRegDate())
            .modDate(entity.getModDate())
            .writerEmail(member.getEmail())
            .writerName(member.getName())
            .replyCount(replyCnt)
            .build();
  }

  default BoardDTO projectionToDTO(BoardWithReplyCount entity) {
    return BoardDTO.builder()
            .bno(entity.board().getBno())
            .title(entity.board().getTitle())
            .content(entity.board().getContent())
            .regDate(entity.board().getRegDate())
            .modDate(entity.board().getModDate())
            .writerEmail(entity.board().getWriter().getEmail())
            .writerName(entity.board().getWriter().getName())
            .replyCount(entity.replyCount())
            .build();
  }
}
