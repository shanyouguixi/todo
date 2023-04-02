package com.shangui.service;

import com.shangui.common.exception.MyException;
import com.shangui.model.ResourceType;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 资源类型 服务类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-31
 */
public interface ResourceTypeService extends IService<ResourceType> {
    List<ResourceType> getResourceType();
    int saveResourceType(ResourceType resource);
    int updateResourceType(ResourceType resource);
    int delResourceType(int id) throws MyException;
}
