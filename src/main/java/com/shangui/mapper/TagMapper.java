package com.shangui.mapper;

import com.shangui.model.Tag;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 标签 Mapper 接口
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-05
 */
public interface TagMapper extends BaseMapper<Tag> {
    List<Tag> getTagList(@Param("userId")int userId);
    int updateTag(Tag tag);

    int addTag(Tag tag);
}
