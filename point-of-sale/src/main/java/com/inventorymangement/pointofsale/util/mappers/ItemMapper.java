package com.inventorymangement.pointofsale.util.mappers;

import com.inventorymangement.pointofsale.dto.response.ItemGetResponseDTO;
import com.inventorymangement.pointofsale.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    List<ItemGetResponseDTO> ListDtoToPage(Page<Item> items);
}
