package com.inventorymangement.pointofsale.dto.paginated;

import com.inventorymangement.pointofsale.dto.response.ItemGetResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaginatedResponseItemDTO {
    List<ItemGetResponseDTO> list;
    private long dataCount;

}
