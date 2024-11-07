package com.inventorymangement.pointofsale.repo;

import com.inventorymangement.pointofsale.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUserName(String userName);
    Boolean existsByUserName(String userName);
}
