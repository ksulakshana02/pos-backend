package com.inventorymangement.pointofsale.service;

import com.inventorymangement.pointofsale.dto.CustomerDTO;
import com.inventorymangement.pointofsale.dto.request.CustomerUpdateDTO;
import com.inventorymangement.pointofsale.service.impl.CustomerServiceIMPL;

import java.util.List;

public interface CustomerService {
    String saveCustomer(CustomerDTO customerDTO);

    String updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    CustomerDTO getCustomerById(int customerId);

    List<CustomerDTO> getAllCustomer();

    String deleteCustomer(int customerId);

    List<CustomerDTO> getAllCustomerByActiveState(boolean activeState);
}
