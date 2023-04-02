package com.shangui.common.dto;


import lombok.Data;

@Data
public class MemoDto {

    private Integer id;
    private Integer pageNum;
    private Integer pageSize;
    private Integer tagId;
    private Integer workspaceId;
    private String searchWord;
    private String title;
    private String content;
    private int userId;
    private Integer startDate;
    private Integer endDate;
}
