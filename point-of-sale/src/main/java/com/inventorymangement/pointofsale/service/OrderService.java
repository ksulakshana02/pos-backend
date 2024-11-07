package com.inventorymangement.pointofsale.service;

import com.inventorymangement.pointofsale.dto.paginated.PaginatedResponseOrderDetails;
import com.inventorymangement.pointofsale.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String saveOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size);
}
