package ko.Inflearn.jpashop.service;

import ko.Inflearn.jpashop.domain.Member;
import ko.Inflearn.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        Member member1 = new Member();
        member1.setName("ko");

        Member member2 = new Member();
        member2.setName("ko");

        memberService.join(member1);

        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }

        fail("예외가 발생해야한다");
    }


}