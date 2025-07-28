package com.devlumi.board.repository.search.order;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Sort;

import java.util.stream.Stream;

public class OrderSpecifierMaker<T> { //사용안함 이제
  public Stream<OrderSpecifier> getOrder(Class<T> tClass, Sort sort) {
    return sort.stream().map(order ->{
      Order direction = order.isAscending() ? Order.ASC : Order.DESC;
      String prop = order.getProperty();

      PathBuilder<T> builder = new PathBuilder<T>(tClass, prop); //prop 이슈 발생 가능성
      return new OrderSpecifier(direction, builder);
    });
  }
}
