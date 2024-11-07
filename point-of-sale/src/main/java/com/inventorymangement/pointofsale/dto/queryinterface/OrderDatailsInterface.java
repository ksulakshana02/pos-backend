package com.inventorymangement.pointofsale.dto.queryinterface;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDatailsInterface {
    String getCustomerName();
    String getCustomerAddress();
    ArrayList getContactNumber();
    Date getDate();
    Double getTotal();
}
