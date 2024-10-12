package restaurant.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderHistoryService orderHistoryService;

    @Mock
    private OrderHistoryRepository orderHistoryRepository;

    @Test
    public void createOrderHistory_shouldGoWell() {
        User user = User.builder()
                .name("name")
                .address("address")
                .surname("surname")
                .mail("mail")
                .build();
        user.setId(UUID.randomUUID());
        Order order = Order.builder()
                .user(user)
                .build();
        order.setId(UUID.randomUUID());
        Long updateTime = OffsetDateTime.now().toInstant().toEpochMilli();
        OrderHistory orderHistory = OrderHistory.builder()
                .order(order)
                .orderStatus(OrderStatus.ORDERED)
                .updateTime(updateTime)
                .build();
        Mockito.when(this.orderHistoryRepository.save(orderHistory)).thenReturn(orderHistory);
        OrderHistory res = orderHistoryService.createOrderHistory(order, OrderStatus.ORDERED, OffsetDateTime.now().toInstant().toEpochMilli());
        Mockito.verify(
                this.orderHistoryRepository,
                Mockito.times(1)
        ).save(orderHistory);
    }
}
