package com.shangui.common.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String userName;
    private String password;
    private String avatar;
    private String salt;
    private String email;
}
