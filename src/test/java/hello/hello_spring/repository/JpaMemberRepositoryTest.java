package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  // 테스트 실행 순서 지정 가능
public class JpaMemberRepositoryTest {

    @Autowired
    private MemberRepository repository;  // @Autowired로 변경

    @Test
    @Order(1)  // 실행 순서를 명확하게 지정할 수 있음
    @DisplayName("회원 저장 테스트")
    public void save() {
        // Given
        Member member = new Member();
        member.setName("testMember1");

        // When
        repository.save(member);
        Optional<Member> result = repository.findById(member.getId());

        // Then
        assertThat(result).isPresent();  // Optional이 비어있지 않은지 확인
        assertThat(result.get()).isEqualTo(member);
    }

    @Test
    @Order(2)
    @DisplayName("이름으로 회원 찾기 테스트")
    public void findByName() {
        // Given
        Member member1 = new Member();
        member1.setName("tester1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("tester2");
        repository.save(member2);

        // When
        Optional<Member> result = repository.findByName("tester1");

        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(member1);
    }

    @Test
    @Order(3)
    @DisplayName("모든 회원 찾기 테스트")
    public void findAll() {
        // Given
        Member member1 = new Member();
        member1.setName("tester1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("tester2");
        repository.save(member2);

        // When
        List<Member> result = repository.findAll();

        // Then
        assertThat(result).hasSize(2);
        assertThat(result).contains(member1, member2);
    }
}
