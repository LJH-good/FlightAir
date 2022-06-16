package com.company.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = {"com.company.demo.dao"} ) // base packages로 지정한 곳에 존재하는 @Mapper로 명시된 Interface 스캔
public class MybatisConfig {
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		
		//application.propertis에서 작성해도 무방
//		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis-mappers/**/*.xml");
//		sessionFactory.setMapperLocations(res);
		
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	}
	
}