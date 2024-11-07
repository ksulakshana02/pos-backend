package com.inventorymangement.pointofsale.controller;


import com.inventorymangement.pointofsale.dto.CustomerDTO;
import com.inventorymangement.pointofsale.dto.request.CustomerUpdateDTO;
import com.inventorymangement.pointofsale.service.CustomerService;
import com.inventorymangement.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody CustomerDTO customerDTO){
        String message = customerService.saveCustomer(customerDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){
        String message = customerService.updateCustomer(customerUpdateDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Updated",message),
                HttpStatus.OK);
    }

    @GetMapping(
            path = "/get-by-id",
            params = "id"
    )
    public ResponseEntity<StandardResponse> getCustomerById(@RequestParam(value = "id") int customerId){
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return  new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"OK",customerDTO),
                HttpStatus.OK
        );
    }

    @GetMapping("/get-all-customer")
    public ResponseEntity<StandardResponse> getAllCustomer(){
        List<CustomerDTO> customerDTOS = customerService.getAllCustomer();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"OK",customerDTOS),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable(value = "id") int customerId){
        String message = customerService.deleteCustomer(customerId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Deleted",message),
                HttpStatus.OK
        );
    }

    @GetMapping("/get-all-customer-by-active-state/{status}")
    public ResponseEntity<StandardResponse> getAllCustomerByActiveState(@PathVariable(value = "status") boolean activeState){
        List<CustomerDTO> allCustomer = customerService.getAllCustomerByActiveState(activeState);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"OK",allCustomer),
                HttpStatus.OK
        );
    }
}
