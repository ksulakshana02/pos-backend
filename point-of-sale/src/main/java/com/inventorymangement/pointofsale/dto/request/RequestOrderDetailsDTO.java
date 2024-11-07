package com.inventorymangement.pointofsale.dto.request;

import com.inventorymangement.pointofsale.entity.Item;
import com.inventorymangement.pointofsale.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestOrderDetailsDTO {
    private String itemName;
    private int qty;
    private double amount;
    private int items;

}
