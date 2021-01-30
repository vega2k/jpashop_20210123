package jpastudy.jpashop.repository;

import jpastudy.jpashop.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    //jpql, criteria, *querydsl 로 구현할 예정
    public List<Order> findAll(OrderSearch orderSearch) {
        return em.createQuery("").getResultList();
    }
}
