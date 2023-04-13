package com.shangui.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-04-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_ebook")
public class Ebook extends Model<Ebook> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String url;
    private String path;
    @TableField("user_id")
    private Integer userId;
    @TableField("create_time")
    private Integer createTime;
    @TableField("update_time")
    private Integer updateTime;
    @TableField("tag_id")
    private Integer tagId;
    private String image;
    private String desc;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
