# 项目说明

是用到的

- jdk1.8
- maven3.5
- springboot
- mysql
- redis
- shiro
- mybatisPlus

# 注意
- 复制application-dev.yml修改为 application-prod.yml  
- 上传文件是需要修改application-prod.yml中的 uploadConfig的文件目录和域名
- 修改WebMvcConfig.java 文件的配置 registry.addResourceHandler("/sources/**").addResourceLocations("file:/usr/local/todoProject/");
保图片链接不会被shiro拦截
- 保证redis的配置正确，否则无法登录
