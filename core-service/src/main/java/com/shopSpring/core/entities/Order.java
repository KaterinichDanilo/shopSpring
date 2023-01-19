package com.shopSpring.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "username")
    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @CreationTimestamp
    private LocalDateTime updateAt;

    @JoinColumn(name = "status")
    private String status;

    public static final class OrderBuilder {
        private Long id;
        private String username;
        private List<OrderItem> orderItems;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
        private String status;

        private OrderBuilder() {
        }

        public static OrderBuilder anOrder() {
            return new OrderBuilder();
        }

        public OrderBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public OrderBuilder withOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public OrderBuilder withCreateAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public OrderBuilder withUpdateAt(LocalDateTime updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public OrderBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setId(id);
            order.setUsername(username);
            order.setOrderItems(orderItems);
            order.setCreateAt(createAt);
            order.setUpdateAt(updateAt);
            order.setStatus(status);
            return order;
        }
    }
}
