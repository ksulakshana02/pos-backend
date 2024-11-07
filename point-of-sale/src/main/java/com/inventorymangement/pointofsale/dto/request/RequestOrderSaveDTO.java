package com.inventorymangement.pointofsale.dto.request;


import com.inventorymangement.pointofsale.entity.Customer;
import com.inventorymangement.pointofsale.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RequestOrderSaveDTO {

    private int customers;
    private Date date;
    private double total;
    private List<RequestOrderDetailsDTO> orderDetails;
}
