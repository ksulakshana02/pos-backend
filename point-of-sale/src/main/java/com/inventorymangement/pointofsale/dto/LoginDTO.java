package com.inventorymangement.pointofsale.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class LoginDTO {
    private String username;
    private String password;
}
