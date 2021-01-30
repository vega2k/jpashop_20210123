package jpastudy.jpashop.repository;

import jpastudy.jpashop.domain.item.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Item item) {
        if(item.getId() == null) {
            em.persist(item);
        }else {
            em.merge(item);
        }
    }

    public Item findOne(Long itemId) {
        return em.find(Item.class, itemId);
    }

    public List<Item> findAll() {
        String sql = "select i from Item i";
        return em.createQuery(sql, Item.class).getResultList();
    }
}
