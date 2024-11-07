package com.inventorymangement.pointofsale.service.impl;

import com.inventorymangement.pointofsale.dto.ItemDTO;
import com.inventorymangement.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.inventorymangement.pointofsale.dto.request.ItemSaveRequestDTO;
import com.inventorymangement.pointofsale.dto.response.ItemGetResponseDTO;
import com.inventorymangement.pointofsale.entity.Customer;
import com.inventorymangement.pointofsale.entity.Item;
import com.inventorymangement.pointofsale.exception.NotFoundException;
import com.inventorymangement.pointofsale.repo.ItemRepo;
import com.inventorymangement.pointofsale.service.ItemService;
import com.inventorymangement.pointofsale.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    ItemRepo itemRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ItemMapper itemMapper;

    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
        item.setActiveState(true);
        if (!itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
            return item.getItemId() + " Saved successful";
        }else {
            throw new DuplicateKeyException("Already added");
        }

    }

    @Override
    public List<ItemSaveRequestDTO> getItemByNameAndStatus(String itemName) {
        boolean b = true;
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveState(itemName,b);
        if (items.size() > 0){
            List<ItemSaveRequestDTO> itemSaveRequestDTOS = modelMapper.map(items, new TypeToken<List<ItemSaveRequestDTO>>() {}.getType());
            return itemSaveRequestDTOS;
        }else {
            throw new RuntimeException("Item is not Active");
        }
    }

//    @Override
//    public List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus) {
//        List<Item> items = itemRepo.findAllByActiveStateEquals(activeStatus);
//        if (items.size()>0){
//            List<ItemGetResponseDTO> itemGetResponseDTOList = modelMapper.map(items, new TypeToken<List<ItemGetResponseDTO>>() {}.getType());
//            return itemGetResponseDTOList;
//        }else {
//            throw new NotFoundException("Item is not active");
//        }
//    }

    @Override
    public PaginatedResponseItemDTO getItemByActiveStatusWithPaginated(boolean activeStatus, int page, int size) {
        Page<Item> items = itemRepo.findAllByActiveStateEquals(activeStatus, PageRequest.of(page, size));

        if (items.getSize()<1){
            throw new NotFoundException("No Data");
        }else {
//            List<ItemGetResponseDTO> itemGetResponseDTO = modelMapper.map(items,new TypeToken<List<ItemGetResponseDTO>>() {}.getType());

            return new PaginatedResponseItemDTO(
                    itemMapper.ListDtoToPage(items),
                    itemRepo.countAllByActiveStateEquals(activeStatus)
            );
        }

    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> customerList = itemRepo.findAll();
        if (customerList.size()>0){
            List<ItemDTO> itemDTOList = modelMapper.map(customerList, new TypeToken<List<ItemDTO>>() {}.getType());
            return itemDTOList;

        }else {
            throw new NotFoundException("No Items");
        }

    }

    @Override
    public String deleteItemById(int itemId) {
        if (itemRepo.existsById(itemId)){
            itemRepo.deleteById(itemId);

            return itemId + " Deleted Successful";
        }else {
            throw new NotFoundException("No Item");
        }
    }
}
