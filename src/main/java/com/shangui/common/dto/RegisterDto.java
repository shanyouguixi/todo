package com.shangui.common.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String userName;
    private String password;
    private String newPassword;
}
