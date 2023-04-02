package com.shangui.common.util;

import lombok.Data;

@Data
public class Result {
    private String msg = "success";
    private Integer code = 0;
    private Object data;
}
