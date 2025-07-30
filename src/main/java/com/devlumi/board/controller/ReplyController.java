package com.devlumi.board.controller;

import com.devlumi.board.domain.dto.ReplyDTO;
import com.devlumi.board.service.ReplyService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("replies")
@RequiredArgsConstructor
public class ReplyController {
  private final ReplyService replyService;

  //@RequestMapping(value = "board/{bon} , method = {RequestMethod.GET, RequestMethod.POST})
//  , produces = MediaType.APPLICATION_JSON_VALUE
  @GetMapping(value = "board/{bno}")
  public ResponseEntity<?> getList(@PathVariable("bno") Long bno){
    log.info(bno);
//    return ResponseEntity.ok(bno);
    return ResponseEntity.ok(replyService.getList(bno));
  }

  @PostMapping("")
  public ResponseEntity<?> createReply(@RequestBody ReplyDTO dto){
    log.info(dto);
    return ResponseEntity.ok(replyService.register(dto));
  }

  @DeleteMapping("/{rno}")
  public ResponseEntity<?> deleteReply(@PathVariable("rno") Long rno){
    log.info(rno + "번 삭제");
    replyService.delete(rno);
    return new ResponseEntity<>("success",HttpStatus.OK);
  }

  @PutMapping("/{rno}")
  public ResponseEntity<?> updateReply(@PathVariable("rno") Long rno,@RequestBody ReplyDTO dto){
    log.info(dto);
    replyService.modify(dto);
    return new ResponseEntity<>("success",HttpStatus.OK);
  }



}
