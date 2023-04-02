package com.shangui.mapper;

import com.shangui.model.Workspace;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-09
 */
public interface WorkspaceMapper extends BaseMapper<Workspace> {
    List<Workspace> getWorkspaceList(@Param("userId") int userId);

    int addWorkspace(Workspace workspace);

    int updateWorkspace(Workspace workspace);
}
