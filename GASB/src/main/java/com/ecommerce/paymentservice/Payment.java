package com.ecommerce.paymentservice;

import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private double amount;
    private String paymentMethod;


    @ManyToOne  // Skapar relation mellan Payment och User
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Kopplar till User-tabellen
    private User user;

    // Getter och Setter för Payment

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

}
