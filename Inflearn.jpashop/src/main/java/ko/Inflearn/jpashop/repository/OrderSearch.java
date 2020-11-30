package ko.Inflearn.jpashop.repository;

import ko.Inflearn.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

}
