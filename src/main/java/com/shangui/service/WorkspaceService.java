package com.shangui.service;

import com.shangui.model.Workspace;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shanyouguixi123
 * @since 2023-03-09
 */
public interface WorkspaceService extends IService<Workspace> {
    List<Workspace> getWorkspaceList();

    int addWorkspace(Workspace workspace);

    int updateWorkspace(Workspace workspace);
}
