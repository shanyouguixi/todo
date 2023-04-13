package com.shangui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.MultipartConfigElement;

/**
 * Hello world!
 */
@MapperScan("com.shangui.mapper")
@SpringBootApplication
public class TodoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  设置单个文件大小
        factory.setMaxFileSize("50MB");//KB 或者 MB 都可以 1MB=1024KB。1KB=1024B(字节)
        /// 设置总上传文件大小
        factory.setMaxRequestSize("50MB");//KB 或者 MB 都可以 1MB=1024KB。1KB=1024B(字节)
        return factory.createMultipartConfig();
    }
}
