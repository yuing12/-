package config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages= {"com.stone"}) //mapper를 이 패키지 밑에서 찾아라 
public class MyBatisConfig {
	@Bean
	public DataSource dataSource() {
		String driverClassName="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/db8?useUnicode=true";
		String username="root";
		String password="1234";
		//DriverManagerDataSource dataSource=new DriverManagerDataSource();//매번 새로 연결객체구함 - 속도가 떨어질수있음
		BasicDataSource dataSource=new BasicDataSource();//connection pooling지원
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
//보편적으로 제일 많이 씀 
	@Bean
	public SqlSessionFactory sqlSessionFactory()throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean.getObject();
	}
		
	@Bean
	public SqlSessionTemplate sqlSessionTemplate()throws Exception{
		return new  SqlSessionTemplate(sqlSessionFactory());		
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}