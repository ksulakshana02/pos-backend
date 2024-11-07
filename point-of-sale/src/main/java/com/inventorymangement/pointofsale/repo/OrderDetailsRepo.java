package com.inventorymangement.pointofsale.repo;

import com.inventorymangement.pointofsale.dto.queryinterface.OrderDatailsInterface;
import com.inventorymangement.pointofsale.dto.response.ResponseOrderDetailsDTO;
import com.inventorymangement.pointofsale.entity.OrderDetails;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Integer> {


}
