package com.shangui.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.shangui.common.dto.EbookDto;
import com.shangui.common.exception.MyException;
import com.shangui.model.Ebook;
import com.shangui.mapper.EbookMapper;
import com.shangui.model.User;
import com.shangui.service.EbookService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-04-04
 */
@Service
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook> implements EbookService {

    @Autowired
    private EbookMapper ebookMapper;
    @Value("${uploadConfig.filePath}")
    private String filePath;
    @Value("${uploadConfig.fileUrl}")
    private String fileUrl;

    @Override
    public List<Ebook> getEbookList(Integer tagId, String name) {
        User userNow = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        Wrapper<Ebook> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        if (tagId != null) wrapper.eq("tag_id", tagId);
        if (name != null) wrapper.like("name", name);
        return ebookMapper.selectList(wrapper);
    }

    @Override
    public int saveEbook(EbookDto dto) {
        User userNow = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = userNow.getId();
        Ebook ebook = new Ebook();
        ebook.setTagId(dto.getTagId());
        ebook.setUrl(dto.getUrl());
        ebook.setName(dto.getName());
        ebook.setPath(dto.getPath());
        ebook.setUserId(userId);
        ebook.setImage(dto.getImage());
        ebook.setDesc(dto.getDesc());
        ebook.setCreateTime((int) System.currentTimeMillis() / 1000);
        ebook.setUpdateTime((int) System.currentTimeMillis() / 1000);
        return ebookMapper.insert(ebook);
    }

    @Override
    public int updateEbook(EbookDto dto) {
        Ebook ebook = new Ebook();
        ebook.setId(dto.getId());
        if (dto.getTagId() != null) ebook.setTagId(dto.getTagId());
        if (dto.getTagId() != null) ebook.setUrl(dto.getUrl());
        if (dto.getName() != null) ebook.setName(dto.getName());
        if (dto.getPath() != null) ebook.setPath(dto.getPath());
        if (dto.getImage() != null) ebook.setImage(dto.getImage());
        if (dto.getDesc() != null) ebook.setDesc(dto.getDesc());
        ebook.setUpdateTime((int) System.currentTimeMillis() / 1000);
        return ebookMapper.updateById(ebook);
    }

    @Override
    public int delEbook(int id) throws MyException {
        Ebook ebook = ebookMapper.selectById(id);
        if (ebook == null) {
            throw new MyException("id错误", 1);
        }
        String url = ebook.getUrl();
        String path = url.replace(fileUrl, filePath);
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        return ebookMapper.deleteById(id);
    }
}
