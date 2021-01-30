package jpastudy.jpashop.service;

import jpastudy.jpashop.domain.Member;
import jpastudy.jpashop.repository.MemberRepositoryDataJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {
    @Autowired
    private MemberRepositoryDataJPA memberRepository;
//    private MemberRepository memberRepository;

//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원 중복 검증
    public void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        //return memberRepository.findOne(memberId);
        //return memberRepository.findById(memberId).get();
        return memberRepository.findById(memberId).orElseThrow(() -> {throw new IllegalStateException("Member Not Exist");});
    }
}
