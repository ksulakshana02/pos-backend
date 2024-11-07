package com.inventorymangement.pointofsale.controller;

import com.inventorymangement.pointofsale.dto.CustomerDTO;
import com.inventorymangement.pointofsale.dto.paginated.PaginatedResponseOrderDetails;
import com.inventorymangement.pointofsale.dto.request.RequestOrderSaveDTO;
import com.inventorymangement.pointofsale.service.OrderService;
import com.inventorymangement.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO){
        String message = orderService.saveOrder(requestOrderSaveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED);
    }

    @GetMapping(
            params = {"stateType","page","size"},
            path = {"/get-order-details"}
    )
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value = "stateType") String stateType,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ){
        PaginatedResponseOrderDetails p = null;
        if (stateType.equalsIgnoreCase("active") | stateType.equalsIgnoreCase("inactive")){
            boolean status = stateType.equalsIgnoreCase("active") ? true : false;
            p = orderService.getAllOrderDetails(status,page,size);
        }

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",p),
                HttpStatus.OK
        );
    }
}
