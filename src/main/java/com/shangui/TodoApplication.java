package com.shangui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@MapperScan("com.shangui.mapper")
@SpringBootApplication
public class TodoApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(TodoApplication.class, args);
    }
}
