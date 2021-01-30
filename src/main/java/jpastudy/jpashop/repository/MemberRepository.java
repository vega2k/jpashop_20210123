package jpastudy.jpashop.repository;

import jpastudy.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    //등록
    public void save(Member member) {
        em.persist(member);
    }
    //Member 하나 Id로 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    //Member 목록 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
    //Member 목록 name으로 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name", name).getResultList();
    }
}
