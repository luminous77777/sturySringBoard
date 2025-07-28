package com.devlumi.board.repository;

import com.devlumi.board.domain.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

  @Autowired
  private MemberRepository repository;

  @Test
  public void testExitst() {
    Assertions.assertNotNull(repository);}

  @Test
  public void insertMembers(){
    IntStream.range(0, 100).forEach(i->{
      Member member = Member.builder()
              .email("user" + i + "@gmail.com")
              .password("password" + i)
              .name ("user" + i)
              .build();
      repository.save(member);
    });
  }
}
