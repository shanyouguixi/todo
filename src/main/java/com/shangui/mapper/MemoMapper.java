package com.shangui.mapper;

import com.shangui.common.dto.MemoDto;
import com.shangui.model.Memo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-05
 */
public interface MemoMapper extends BaseMapper<Memo> {
    List<Memo> getMemoList(MemoDto memoDto);
}
