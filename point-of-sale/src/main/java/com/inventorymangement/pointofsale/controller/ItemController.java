package com.inventorymangement.pointofsale.controller;

import com.inventorymangement.pointofsale.dto.ItemDTO;
import com.inventorymangement.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.inventorymangement.pointofsale.dto.request.ItemSaveRequestDTO;
import com.inventorymangement.pointofsale.dto.response.ItemGetResponseDTO;
import com.inventorymangement.pointofsale.repo.ItemRepo;
import com.inventorymangement.pointofsale.service.ItemService;
import com.inventorymangement.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    ItemService itemService;

//    @GetMapping("/save")
//    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO){
//        String message = itemService.saveItem(itemSaveRequestDTO);
//        return message;
//    }

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveItem(ItemSaveRequestDTO itemSaveRequestDTO){
        String message = itemService.saveItem(itemSaveRequestDTO);

//        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
//                new StandardResponse(201,"Success",message),
//                HttpStatus.CREATED
//        );
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/get-all-items")
    public ResponseEntity<StandardResponse> getAllItems(){
        List<ItemDTO> itemDTOS = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Ok",itemDTOS),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path ="/get-by-name",
            params = "name"
    )
    public ResponseEntity<StandardResponse> getItemByNameAndStatus(@RequestParam(value = "name") String itemName){
        List<ItemSaveRequestDTO> itemSaveRequestDTOS = itemService.getItemByNameAndStatus(itemName);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"OK",itemSaveRequestDTOS),
                HttpStatus.OK);
    }

    @GetMapping(
            path ="/get-by-activestate",
            params = {"activeStatus","page","size"}
    )
    public ResponseEntity<StandardResponse> getItemByActiveStatus(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
            ){
//        List<ItemGetResponseDTO> itemGetResponseDTOS = itemService.getItemByActiveStatus(activeStatus,page,size );
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getItemByActiveStatusWithPaginated(activeStatus,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"OK",paginatedResponseItemDTO),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete-item/{id}")
    public ResponseEntity<StandardResponse> deleteItemById(@PathVariable(value = "id") int itemId){
        String message = itemService.deleteItemById(itemId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Ok",message),
                HttpStatus.OK
        );
    }
}
