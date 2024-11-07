package com.inventorymangement.pointofsale.service.impl;

import com.inventorymangement.pointofsale.dto.paginated.PaginatedResponseOrderDetails;
import com.inventorymangement.pointofsale.dto.queryinterface.OrderDatailsInterface;
import com.inventorymangement.pointofsale.dto.request.RequestOrderSaveDTO;
import com.inventorymangement.pointofsale.dto.response.ResponseOrderDetailsDTO;
import com.inventorymangement.pointofsale.entity.Order;
import com.inventorymangement.pointofsale.entity.OrderDetails;
import com.inventorymangement.pointofsale.exception.NotFoundException;
import com.inventorymangement.pointofsale.repo.CustomerRepo;
import com.inventorymangement.pointofsale.repo.ItemRepo;
import com.inventorymangement.pointofsale.repo.OrderDetailsRepo;
import com.inventorymangement.pointofsale.repo.OrderRepo;
import com.inventorymangement.pointofsale.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private ItemRepo itemRepo;



    @Override
    @Transactional
    public String saveOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order = new Order(
                customerRepo.getById(requestOrderSaveDTO.getCustomers()),
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal()
        );
        orderRepo.save(order);

        if (orderRepo.existsById(order.getOrderId())){
            List<OrderDetails> orderDetails = modelMapper.map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>() {}.getType());

            for (int i=0;i<orderDetails.size();i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));
            }
            if (orderDetails.size()>0){
                orderDetailsRepo.saveAll(orderDetails);

            }
            return "Saved";
        }
        throw new NotFoundException("Error");
    }

    @Override
    public PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size) {
        List<OrderDatailsInterface> orderDetailsDTOS = orderRepo.getAllOrderDetails(status, PageRequest.of(page, size));

        List<ResponseOrderDetailsDTO> list = new ArrayList<>();
        for (OrderDatailsInterface o: orderDetailsDTOS){
            ResponseOrderDetailsDTO r = new ResponseOrderDetailsDTO(
                    o.getCustomerName(),
                    o.getCustomerAddress(),
                    o.getContactNumber(),
                    o.getDate(),
                    o.getTotal()
            );
            list.add(r);
        }
        PaginatedResponseOrderDetails paginatedResponseOrderDetails = new PaginatedResponseOrderDetails(
                list,
                orderRepo.countAllOrderDetails(status)
        );
        return paginatedResponseOrderDetails;
    }
}
