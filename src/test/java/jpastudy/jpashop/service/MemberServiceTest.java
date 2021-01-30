package jpastudy.jpashop.service;

import jpastudy.jpashop.domain.Member;
import jpastudy.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        Member member = new Member();
        member.setName("부트");

        Long saveId = memberService.join(member);

        assertEquals(member, memberRepository.findOne(saveId));

    }

    @Test(expected = IllegalStateException.class)
    public void 회원중복() throws Exception {
        Member member = new Member();
        member.setName("부트");

        Member member2 = new Member();
        member2.setName("부트");

        memberService.join(member);
//        try {
        memberService.join(member2);
//        }catch (IllegalStateException e) {
//            return;
//        }

        fail("중복예외가 발생되었음");
    }

}