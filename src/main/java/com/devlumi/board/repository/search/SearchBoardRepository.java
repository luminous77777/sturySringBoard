package com.devlumi.board.repository.search;

import com.devlumi.board.domain.entity.Board;
import com.devlumi.board.domain.projection.dto.BoardWithReplyCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository extends SearchSupport<Board>{
  Board search1();

  Page<BoardWithReplyCount> searchPage(String type, String keyword, Pageable pageable);
}
