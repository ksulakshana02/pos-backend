package com.inventorymangement.pointofsale.dto.request;

import com.inventorymangement.pointofsale.entity.enums.MeasuringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemSaveRequestDTO {
    private String itemName;
    private int balanceQty;
    private MeasuringType measuringType;
    private double itemPrice;
}
