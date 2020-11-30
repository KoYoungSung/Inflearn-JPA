package ko.Inflearn.jpashop.service;

import ko.Inflearn.jpashop.domain.Delivery;
import ko.Inflearn.jpashop.domain.Member;
import ko.Inflearn.jpashop.domain.Order;
import ko.Inflearn.jpashop.domain.OrderItem;
import ko.Inflearn.jpashop.domain.item.Item;
import ko.Inflearn.jpashop.repository.ItemRepository;
import ko.Inflearn.jpashop.repository.MemberRepository;
import ko.Inflearn.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {


    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }


}
