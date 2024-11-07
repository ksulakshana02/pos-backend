package com.inventorymangement.pointofsale.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemGetResponseDTO {
    private int itemId;
    private String itemName;
    private int balanceQty;
    private double itemPrice;
    private boolean activeState;
}
