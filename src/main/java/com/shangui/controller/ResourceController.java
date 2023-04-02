package com.shangui.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangui.common.dto.ResourceDto;
import com.shangui.common.util.Result;
import com.shangui.model.Resource;
import com.shangui.model.ResourceType;
import com.shangui.model.Tag;
import com.shangui.service.ResourceService;
import com.shangui.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceTypeService resourceTypeService;

    @Value("${uploadConfig.filePath}")
    private String filePath;
    @Value("${uploadConfig.fileUrl}")
    private String fileUrl;

    @PostMapping("/multiUpload")
    public Result multiUpload(@RequestParam("file") List<MultipartFile> files) {
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            Map<String, String> map = new HashMap<>();
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                continue;
            }
            String oldFileName = file.getOriginalFilename();
            String fileName = UUID.randomUUID() + "."
                    + oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
            String fullPath = filePath + fileName;
            String fullUrl = fullPath.replace(filePath, fileUrl);
            File dest = new File(fullPath);
            try {
                file.transferTo(dest);
                map.put("fileName", oldFileName);
                map.put("fileUrl", fullUrl);
                list.add(map);
            } catch (IOException e) {

            }
        }
        Result result = new Result();
        result.setData(list);
        result.setMsg("上传成功");
        return result;
    }


    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        Result result = new Result();
        String oldFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "."
                + oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
        String fullPath = filePath + fileName;
        String fullUrl = fullPath.replace(filePath, fileUrl);
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            Map<String, String> map = new HashMap<>();
            map.put("fileName", oldFileName);
            map.put("fileUrl", fullUrl);
            result.setData(map);
            result.setMsg("上传成功");
        } catch (IOException e) {
            result.setCode(1);
            result.setMsg("上传失败");
        }
        return result;

    }


    @GetMapping("/getResource")
    public Result getResourceList(@RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("pageNum") Integer pageNum,
                                  @RequestParam(name = "typeId", required = false) Integer typeId,
                                  @RequestParam(name = "fileName", required = false) String fileName) {
        PageHelper.startPage(pageNum, pageSize);
        List<Resource> resources = resourceService.getResourceByTypeId(typeId, fileName);
        PageInfo<Resource> pageInfo = new PageInfo<>(resources);
        Result result = new Result();
        result.setData(pageInfo);
        return result;
    }


    @GetMapping("/getResourceType")
    public Result getResourceTypeList(@RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<ResourceType> resources = resourceTypeService.getResourceType();
        PageInfo<ResourceType> pageInfo = new PageInfo<>(resources);
        Result result = new Result();
        result.setData(pageInfo);
        return result;
    }


    @PostMapping("/saveResource")
    public Result saveResource(@RequestParam(value = "file") MultipartFile[] files, @RequestParam("typeId") Integer typeId) {
        Result result = new Result();
        try {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                if (file.isEmpty()) {
                    continue;
                }
                String oldFileName = file.getOriginalFilename();
                String fileName = UUID.randomUUID() + "."
                        + oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
                String fullPath = filePath + fileName;
                String fullUrl = fullPath.replace(filePath, fileUrl);
                File dest = new File(fullPath);
                try {
                    file.transferTo(dest);
                    ResourceDto dto = new ResourceDto();
                    dto.setFileName(fileName);
                    dto.setUrl(fullUrl);
                    dto.setTypeId(typeId);
                    resourceService.saveResource(dto);
                } catch (IOException e) {

                }
            }
            result.setMsg("上传成功");
        } catch (MultipartException e) {
            result.setCode(1);
            result.setMsg("上传文件必须小于50M");
        } catch (Exception e) {
            result.setCode(1);
            result.setMsg("上传失败");
        }
        return result;
    }

    @PostMapping("/updateResource")
    public Result updateResource(@RequestBody ResourceDto dto) {
        resourceService.updateResource(dto);
        Result result = new Result();
        result.setMsg("编辑成功");
        return result;
    }

    @DeleteMapping("/delResource")
    public Result delResource(@RequestParam int id) {
        Result result = new Result();
        try {
            resourceService.delResource(id);
        } catch (Exception e) {
            result.setCode(1);
            result.setMsg("删除失败");
            return result;
        }
        result.setMsg("编辑成功");
        return result;
    }


    @PostMapping("/updateResourceType")
    public Result updateResourceType(@RequestBody ResourceType resourceType) {
        resourceTypeService.updateResourceType(resourceType);
        Result result = new Result();
        result.setMsg("编辑成功");
        return result;
    }

    @PostMapping("/addResourceType")
    public Result addResourceType() {
        ResourceType resourceType = new ResourceType();
        resourceType.setTypeName("新分类");
        resourceTypeService.saveResourceType(resourceType);
        Result result = new Result();
        result.setMsg("编辑成功");
        return result;
    }

    @DeleteMapping("/delResourceType")
    public Result delResourceType(@RequestParam int id) {
        Result result = new Result();
        try {
            resourceTypeService.delResourceType(id);
        } catch (Exception e) {
            result.setCode(1);
            result.setMsg("删除失败");
            return result;
        }
        result.setMsg("编辑成功");
        return result;
    }

}
