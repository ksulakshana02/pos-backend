package com.inventorymangement.pointofsale.service;

import com.inventorymangement.pointofsale.dto.ItemDTO;
import com.inventorymangement.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.inventorymangement.pointofsale.dto.request.ItemSaveRequestDTO;
import com.inventorymangement.pointofsale.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemSaveRequestDTO> getItemByNameAndStatus(String itemName);

//    List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus);


    PaginatedResponseItemDTO getItemByActiveStatusWithPaginated(boolean activeStatus, int page, int size);

    List<ItemDTO> getAllItems();

    String deleteItemById(int itemId);
}
