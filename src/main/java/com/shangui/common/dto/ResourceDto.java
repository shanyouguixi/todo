package com.shangui.common.dto;

import lombok.Data;

@Data
public class ResourceDto {
    private Integer id;
    private int pageSize;
    private int pageNum;
    private Integer typeId;
    private String fileName;
    private String url;
}
