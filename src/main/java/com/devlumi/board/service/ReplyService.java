package com.devlumi.board.service;


import com.devlumi.board.domain.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {

  Long register(ReplyDTO dto);

  List<ReplyDTO> getList(Long bno);

  void modify(ReplyDTO dto);

  void remove(Long rno);

  ReplyDTO get(Long rno);
}
