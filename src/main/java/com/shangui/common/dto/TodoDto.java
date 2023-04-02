package com.shangui.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TodoDto {
    private Integer pageNum;
    private Integer pageSize;
    private Integer tagId;
    private Integer userId;
    private Integer workspaceId;
    private String searchWord;
    private String title;
    private String content;

    private Integer startDate;
    @JsonFormat(locale="zh", timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date remarkDate;
    private Integer endDate;
}
