package com.shangui.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.shangui.common.exception.MyException;
import com.shangui.model.Resource;
import com.shangui.model.ResourceType;
import com.shangui.mapper.ResourceTypeMapper;
import com.shangui.model.User;
import com.shangui.service.ResourceService;
import com.shangui.service.ResourceTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源类型 服务实现类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-31
 */
@Service
public class ResourceTypeServiceImpl extends ServiceImpl<ResourceTypeMapper, ResourceType> implements ResourceTypeService {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ResourceTypeMapper resourceTypeMapper;

    @Override
    public List<ResourceType> getResourceType() {
        User userNow = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        Wrapper<ResourceType> wrapper = new EntityWrapper();
        wrapper.eq("user_id",userId);
        return resourceTypeMapper.selectList(wrapper);
    }

    @Override
    public int saveResourceType(ResourceType resource) {
        User userNow = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        resource.setUserId(userId);
        resource.setCreateTime((int)System.currentTimeMillis()/1000);
        resource.setUpdateTime((int)System.currentTimeMillis()/1000);
        return resourceTypeMapper.insert(resource);
    }

    @Override
    public int updateResourceType(ResourceType resource) {
        resource.setUpdateTime((int)System.currentTimeMillis()/1000);
        return resourceTypeMapper.updateById(resource);
    }

    @Override
    public int delResourceType(int id) throws MyException {
        List<Resource> resources = resourceService.getResourceByTypeId(id, null);
        if (resources.size() > 0){
            throw new MyException("分类下有文件不能删除",1);
        }
        return resourceTypeMapper.deleteById(id);
    }
}
