package com.devlumi.board.service;

import com.devlumi.board.domain.dto.ReplyDTO;
import com.devlumi.board.domain.mapper.ReplyMapper;
import com.devlumi.board.repository.ReplyRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class ReplyServiceImpl implements ReplyService{
  private final ReplyMapper replyMapper;
  private final ReplyRepository replyRepository;

  @Override
  public Long register(ReplyDTO dto) {
    return replyRepository.save(replyMapper.toEntity(dto)).getRno();
  }

  @Override
  public List<ReplyDTO> getList(Long bno) {
    return replyRepository.findByBoard_BnoOrderByRno(bno).stream().map(replyMapper::toDto).toList();
  }

  @Override
  public void modify(ReplyDTO dto) {
    replyRepository.save(replyMapper.toEntity(dto));
  }

  @Override
  public void remove(Long rno) {
    replyRepository.deleteById(rno);
  }

  @Override
  public ReplyDTO get(Long rno) {
    return replyMapper.toDto(replyRepository.findById(rno).orElseThrow(() -> new IllegalArgumentException("댓글 없음")));
  }
}
