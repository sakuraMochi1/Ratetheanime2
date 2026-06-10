package com.duoc.ratetheanime.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}