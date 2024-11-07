package com.inventorymangement.pointofsale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_details")

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDetails {

    @Id
    @Column(name = "order_details_id",length = 10)
    private int orderDetailsId;

    @Column(name = "item_name", length = 100 , nullable = false)
    private String itemName;

    @Column(name = "qty" , length = 5, nullable = false)
    private int qty;

    @Column(name = "amount",nullable = false,length = 10)
    private double amount;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item items;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order orders;
}
