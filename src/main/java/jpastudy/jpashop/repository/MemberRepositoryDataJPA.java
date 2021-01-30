package jpastudy.jpashop.repository;

import jpastudy.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepositoryDataJPA extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
}
