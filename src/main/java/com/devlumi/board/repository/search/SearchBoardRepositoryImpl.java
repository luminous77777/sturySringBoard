package com.devlumi.board.repository.search;

import com.devlumi.board.domain.entity.Board;
import com.devlumi.board.domain.entity.QBoard;
import com.devlumi.board.domain.entity.QMember;
import com.devlumi.board.domain.entity.QReply;
import com.devlumi.board.domain.projection.dto.BoardWithReplyCount;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import java.util.List;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements  SearchBoardRepository {

  public SearchBoardRepositoryImpl() {
    super(Board.class);
  }

  @Override
  public Board search1() {
    log.info("----------------------------------------");
    QBoard qBoard = QBoard.board;
    JPQLQuery<Board> jpqlQuery = from(qBoard);
    jpqlQuery.where(qBoard.bno.gt(0L));
    log.info(jpqlQuery);

    //member 조인 변수로 지정하지 않아도 되는것은 static이기 때문
    jpqlQuery
            .leftJoin(QMember.member).on(qBoard.writer.eq(QMember.member))
            .leftJoin(QReply.reply).on(QReply.reply.board.eq(qBoard));

    JPQLQuery<Tuple> tuple =jpqlQuery.select(qBoard, QMember.member, QReply.reply.count()).groupBy(qBoard).limit(10);
//    QMember.member.email

    log.info(tuple); //tuple 프로젝션 dto로 바꾸어야한다
    List<Tuple> list =  tuple.fetch();
    log.info(list);
    log.info("----------------------------------------");
    return null;
  }

  @Override
  public Page<BoardWithReplyCount> searchPage(String type, String keyword, Pageable pageable) {
    QBoard board = QBoard.board;
    QReply reply = QReply.reply;
    QMember member = QMember.member;

    //조인
    JPQLQuery<Board> jpqlQuery = from(board);
    jpqlQuery
            .leftJoin(member).on(board.writer.eq(member))
            .leftJoin(reply).on(reply.board.eq(board));

    JPQLQuery<Tuple> tuple = jpqlQuery.groupBy(board).select(board, member, reply.count());

    //검색
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    BooleanExpression expression = board.bno.gt(0L);
    booleanBuilder.and(expression);

    if(!(type == null || type.trim().length() == 0)){
//      return booleanBuilder;

      BooleanBuilder conditionBuilder = new BooleanBuilder();
      if(type.contains("t")){
        conditionBuilder.or(board.title.contains(keyword));
      }
      if(type.contains("c")){
        conditionBuilder.or(board.content.contains(keyword));
      }
      if(type.contains("w")){
        conditionBuilder.or(member.name.contains(keyword));
      }
      booleanBuilder.and(conditionBuilder);
    }
    tuple.where(booleanBuilder); //여기까지가 검색 반영
//    tuple.orderBy(board.bno.desc());

    getOrder(Board.class, pageable.getSort()).forEach(tuple::orderBy);

    //페이지 적용
    tuple.limit(pageable.getPageSize());
    tuple.offset(pageable.getOffset());

//    List<?> list = tuple.fetch();
//    list.forEach(log::info);
    //DTO Projection 변환
    JPQLQuery<BoardWithReplyCount> query = tuple.select(Projections.constructor(BoardWithReplyCount.class , board, member, reply.count()));

    // page 타입 변환
    return new PageImpl<>(query.fetch(), pageable, tuple.fetchCount());
  }


}
