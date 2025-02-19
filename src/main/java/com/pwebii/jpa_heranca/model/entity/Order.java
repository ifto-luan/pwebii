package com.pwebii.jpa_heranca.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Component
@Scope("session")
@Table(name="e_order")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order() { }

    public BigDecimal calculateTotal() {
        return items.stream().map(OrderItem::total).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItem(Product p, double quantity) {

        for (OrderItem item : items) {
            
            if (item.getProduct().getId().equals(p.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
            
        }
        
        OrderItem item = new OrderItem();
        item.setProduct(p);
        item.setQuantity(quantity);
        item.setOrder(this);
        this.items.add(item);
    }
    
    public void removeItem(Long productId) {
        items.removeIf(x -> x.getProduct().getId().equals(productId));
    }

    public void removeItem(Long productId, double quantity) {

        for (OrderItem item : items) {
            
            if (item.getProduct().getId().equals(productId)) {

                if (item.getQuantity() <= quantity) {

                    removeItem(productId);
                    return;

                }

                item.setQuantity(item.getQuantity() - quantity);
                return;
            }
            
        }
    }

    public void clear() {
        items.clear();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", date=" + date + ", client=" + client + ", items=" + items + "]";
    }

    

}
