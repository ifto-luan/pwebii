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

@Entity
@Component
@Scope("session")
public class Sale implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Person client;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<SaleItem> items = new ArrayList<>();

    public Sale() { }

    public BigDecimal calculateTotal() {
        return items.stream().map(SaleItem::total).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItem(Product p, double quantity) {

        for (SaleItem item : items) {
            
            if (item.getProduct().getId().equals(p.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
            
        }
        
        SaleItem item = new SaleItem();
        item.setProduct(p);
        item.setQuantity(quantity);
        item.setSale(this);
        this.items.add(item);
    }
    
    public void removeItem(Long productId) {
        items.removeIf(x -> x.getProduct().getId().equals(productId));
    }

    public void removeItem(Long productId, double quantity) {

        for (SaleItem item : items) {
            
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

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Sale [id=" + id + ", date=" + date + ", client=" + client + ", items=" + items + "]";
    }

    

}
