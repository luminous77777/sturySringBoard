package com.devlumi.board.service;

import com.devlumi.board.dto.BoardDTO;
import com.devlumi.board.dto.PageRequestDTO;
import com.devlumi.board.dto.PageResponseDTO;
import com.devlumi.board.projection.dto.BoardWithReplyCount;
import com.devlumi.board.repository.BoardRepository;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Log4j2
@Data
@Service
public class BoardServiceImpl implements BoardService{

  private final BoardRepository boardRepository;

  @Override
  public Long register(BoardDTO boardDTO) {

    return boardRepository.save(toEntity(boardDTO)).getBno();
  }

  @Override
  public PageResponseDTO<BoardDTO, BoardWithReplyCount> getList(PageRequestDTO pageRequestDTO) {
    return new PageResponseDTO<>(
            boardRepository.getBoardWithReplyCount3(pageRequestDTO.getPageable(Sort.by(Sort.Direction.DESC,"bno")))
            , this::projectionToDTO);
  }

  @Override
  public BoardDTO get(Long bno) {
    return projectionToDTO(boardRepository.getBoardByBno(bno));
  }
}
