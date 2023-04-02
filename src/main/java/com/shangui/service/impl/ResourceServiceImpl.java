package com.shangui.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.shangui.common.dto.ResourceDto;
import com.shangui.common.exception.MyException;
import com.shangui.model.Resource;
import com.shangui.mapper.ResourceMapper;
import com.shangui.model.ResourceType;
import com.shangui.model.User;
import com.shangui.service.ResourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-31
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Value("${uploadConfig.filePath}")
    private String filePath;
    @Value("${uploadConfig.fileUrl}")
    private String fileUrl;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> getResourceByTypeId(Integer typeId, String fileName) {
        User userNow = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        Wrapper<Resource> wrapper = new EntityWrapper();
        wrapper.eq("user_id", userId);
        if (fileName != null) wrapper.like("file_name", fileName);
        if (typeId != null)wrapper.eq("type_id", typeId);
        wrapper.orderBy("id",false);
        return resourceMapper.selectList(wrapper);
    }

    @Override
    public int saveResource(ResourceDto dto) {
        User userNow = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        Resource resource = new Resource();
        resource.setFileName(dto.getFileName());
        resource.setUrl(dto.getUrl());
        resource.setUserId(userId);
        resource.setTypeId(dto.getTypeId());
        resource.setCreateTime((int) System.currentTimeMillis() / 1000);
        resource.setUpdateTime((int) System.currentTimeMillis() / 1000);
        return resourceMapper.insert(resource);
    }

    @Override
    public int updateResource(ResourceDto dto) {
        User userNow = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        Resource resource = new Resource();
        resource.setId(dto.getTypeId());
        if (dto.getFileName() != null) resource.setFileName(dto.getFileName());
        if (dto.getTypeId() != null) resource.setTypeId(dto.getTypeId());
        resource.setUserId(userId);
        resource.setUpdateTime((int) System.currentTimeMillis() / 1000);
        return resourceMapper.updateById(resource);
    }

    @Override
    public int delResource(int id) throws MyException {
        Resource resource = resourceMapper.selectById(id);
        if (resource == null) {
            throw new MyException("id错误", 1);
        }
        String url = resource.getUrl();
        String path = url.replace(fileUrl, filePath);
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        return resourceMapper.deleteById(id);
    }
}
