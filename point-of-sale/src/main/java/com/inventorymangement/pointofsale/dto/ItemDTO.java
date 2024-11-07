package com.inventorymangement.pointofsale.dto;

import com.inventorymangement.pointofsale.entity.enums.MeasuringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemDTO {
    private int itemId;
    private String itemName;
    private int balanceQty;
    private MeasuringType measuringType;
    private double itemPrice;
    private boolean activeState;
}
