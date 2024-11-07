package com.inventorymangement.pointofsale.service.impl;

import com.inventorymangement.pointofsale.dto.CustomerDTO;
import com.inventorymangement.pointofsale.dto.request.CustomerUpdateDTO;
import com.inventorymangement.pointofsale.entity.Customer;
import com.inventorymangement.pointofsale.exception.NotFoundException;
import com.inventorymangement.pointofsale.repo.CustomerRepo;
import com.inventorymangement.pointofsale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    CustomerRepo customerRepo;
    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getContactNumber(),
                customerDTO.isActive()
        );
        customerRepo.save(customer);
        return customerDTO.getCustomerName() + " Saved Successful";
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if (customerRepo.existsById(customerUpdateDTO.getCustomerId())){
            Customer customer = customerRepo.getReferenceById(customerUpdateDTO.getCustomerId());

            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());

            customerRepo.save(customer);
            return  customerUpdateDTO.getCustomerName() + " Updated Successful";

        }else {
            throw new RuntimeException("No data found that ID");
        }
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if (customerRepo.existsById(customerId)){
            Customer customer = customerRepo.getReferenceById(customerId);
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getContactNumber(),
                    customer.isActive()
            );
            return customerDTO;

        }else {
            throw new RuntimeException("No Customer");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> getAllCustomer = customerRepo.findAll();

        if (getAllCustomer.size()>0) {
            List<CustomerDTO> customerDTOList = new ArrayList<>();

            for (Customer customer : getAllCustomer) {
                CustomerDTO customerDTO = new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getCustomerAddress(),
                        customer.getContactNumber(),
                        customer.isActive()
                );
                customerDTOList.add(customerDTO);
            }
            return customerDTOList;
        }else {
            throw new NotFoundException("Not found customer");




        }
    }

    @Override
    public String deleteCustomer(int customerId) {
        if (customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);
            return "Deleted successful " + customerId;
        }else {
            throw new RuntimeException("No customer");
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomerByActiveState(boolean activeState) {
        List<Customer> customers = customerRepo.findAllByActiveEquals(activeState);

        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer: customers){
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getContactNumber(),
                    customer.isActive()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}

