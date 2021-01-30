package jpastudy.jpashop.domain;

import jpastudy.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="order_item")
@Getter @Setter
//기본생성자의 접근제한자를 PROTECTED 로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    private int orderPrice;

    private int count;

    //주문취소일 경우에 재고수량 증가
    public void cancel() {
        item.addStock(count);
    }

    //주문상품의 전체가격 가격 * 수량
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

    //==주문상품 생성 메서드==//
    //OrderService에서 주문을 처리할 때 호출됨
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;
    }
}
