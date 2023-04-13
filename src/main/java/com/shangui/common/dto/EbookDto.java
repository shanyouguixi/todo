package com.shangui.common.dto;

import lombok.Data;

@Data
public class EbookDto {
    private Integer id;
    private String name;
    private String path;
    private String url;
    private Integer tagId;
    private String image;
    private String desc;
}
