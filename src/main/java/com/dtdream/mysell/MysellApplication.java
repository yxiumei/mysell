package com.dtdream.mysell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 杨秀眉
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.dtdream.mysell.mapper"})
public class MysellApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysellApplication.class, args);
	}
}
