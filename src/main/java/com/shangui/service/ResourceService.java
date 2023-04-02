package com.shangui.service;

import com.shangui.common.dto.ResourceDto;
import com.shangui.common.exception.MyException;
import com.shangui.model.Resource;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-31
 */
public interface ResourceService extends IService<Resource> {
    List<Resource> getResourceByTypeId(Integer typeId,String fileName);
    int saveResource(ResourceDto resource);
    int updateResource(ResourceDto resource);
    int delResource(int id) throws MyException;
}
