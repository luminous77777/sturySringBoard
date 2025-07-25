package com.devlumi.board.service;

import com.devlumi.board.dto.BoardDTO;
import com.devlumi.board.dto.PageRequestDTO;
import com.devlumi.board.dto.PageResponseDTO;
import com.devlumi.board.entity.Board;
import com.devlumi.board.projection.dto.BoardWithReplyCount;
import com.devlumi.board.repository.BoardRepository;
import com.devlumi.board.repository.ReplyRepository;
import jakarta.transaction.Transactional;
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
  private final ReplyRepository replyRepository;

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

  @Override
  @Transactional
  public void remove(Long bno) {
    replyRepository.deleteByBoard_Bno(bno);
    boardRepository.deleteById(bno);
  }

  @Override
  public void modify(BoardDTO boardDTO) {
    Board board = boardRepository.findById(boardDTO.getBno()).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));
    board.changeTitle(boardDTO.getTitle());
    board.changeContent(boardDTO.getContent());
    boardRepository.save(board);
  }
}
