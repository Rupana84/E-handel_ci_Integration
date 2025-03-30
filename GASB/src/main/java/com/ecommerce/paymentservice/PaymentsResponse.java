package com.ecommerce.paymentservice;

public class PaymentsResponse {
    private Payment payment;
    private User user;

    public PaymentsResponse(Payment payment, User user) {
        this.payment = payment;
        this.user = user;
    }

    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
