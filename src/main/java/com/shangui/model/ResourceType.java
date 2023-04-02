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
 * 资源类型
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_resource_type")
public class ResourceType extends Model<ResourceType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("type_name")
    private String typeName;
    @TableField("user_id")
    private Integer userId;
    @TableField("create_time")
    private Integer createTime;
    @TableField("update_time")
    private Integer updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
