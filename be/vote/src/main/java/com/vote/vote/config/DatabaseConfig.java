package com.vote.vote.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration // 1개만 생성, 설정파일.
@PropertySource("classpath:/application.properties")//DB 설정 주소 가져오기
public class DatabaseConfig {
	
	@Autowired // spring이 부팅하면서 만들어 놓은것을 줌 ( 레퍼런스 등을 줌)
	private ApplicationContext  applicationContext;
	
	@Bean //시스템에서 사용할 객체   spring이 부팅할 때 메모리에 잡음.
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() throws Exception{
		DataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}
	


	//prifix="설정부분 " 설정부분만 읽어 옴. config 객체 : DB 관련된 정보 가져와서
	
	// 데이터 소스를 만듦.
}
