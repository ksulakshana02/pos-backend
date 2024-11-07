package com.inventorymangement.pointofsale.entity;

import com.inventorymangement.pointofsale.entity.enums.MeasuringType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item {

    @Id
    @Column(name = "item_id", length = 10)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "item_name", length = 100 , nullable = false)
    private String itemName;

    @Column(name = "qty" , length = 5, nullable = false)
    private int balanceQty;

    @Enumerated(EnumType.STRING)
    @Column(name = "measuring_type",length = 10,nullable = false)
    private MeasuringType measuringType;

    @Column(name = "item_price", length = 10, nullable = false)
    private double itemPrice;

    @Column(name = "active_sate", columnDefinition = "TINYINT default 0")
    private boolean activeState;

    @OneToMany(mappedBy="items")
    private Set<OrderDetails> orderDetails;
}
