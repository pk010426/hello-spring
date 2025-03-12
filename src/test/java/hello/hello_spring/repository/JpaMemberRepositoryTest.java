package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    private final JpaMemberRepository repository;

    public MemoryMemberRepositoryTest(JpaMemberRepository repository) {
        this.repository = repository;
    }

    @Test
    public void save() {

        Member member = new Member();
        member.setName("testMember1");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();

//        System.out.println("resule = " + (result == member));

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("tester1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("tester2");
        repository.save(member2);

        Member result = repository.findByName("tester1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("tester1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("tester2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
