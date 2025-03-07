package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    MemoryMemberRepository repository;


    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void join() {

        // given
        Member member = new Member();
        member.setName("tester1");

        //when
        Long saveId = memberService.join(member);
        Member findMember = memberService.findOne(saveId).get();

        //then
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void validationJoin() {
        // given
        Member member1 = new Member();
        member1.setName("tester");

        Member member2 = new Member();
        member2.setName("tester");

        // when
        memberService.join(member1);


//        assertThrows(NullPointerException.class, () -> memberService.join(member2));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            System.out.println(e.getMessage());
//        }

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}