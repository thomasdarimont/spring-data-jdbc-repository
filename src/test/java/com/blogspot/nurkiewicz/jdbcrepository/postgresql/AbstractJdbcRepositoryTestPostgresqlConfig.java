package com.blogspot.nurkiewicz.jdbcrepository.postgresql;

import com.blogspot.nurkiewicz.jdbcrepository.AbstractJdbcRepositoryTestConfig;
import com.blogspot.nurkiewicz.jdbcrepository.repositories.BoardingPassRepository;
import com.blogspot.nurkiewicz.jdbcrepository.repositories.CommentRepository;
import com.blogspot.nurkiewicz.jdbcrepository.repositories.UserRepository;
import com.blogspot.nurkiewicz.jdbcrepository.sql.PostgreSqlGenerator;
import com.blogspot.nurkiewicz.jdbcrepository.sql.SqlGenerator;
import org.postgresql.jdbc2.optional.PoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@Configuration
public class AbstractJdbcRepositoryTestPostgresqlConfig extends AbstractJdbcRepositoryTestConfig {

	public static final int POSTGRESQL_PORT = 5432;

	@Bean
	@Override
	public CommentRepository commentRepository() {
		return new CommentRepository("comments");
	}

	@Bean
	@Override
	public UserRepository userRepository() {
		return new UserRepository("users");
	}

	@Override
	public BoardingPassRepository boardingPassRepository() {
		return new BoardingPassRepository("boarding_pass");
	}

	@Bean
	public SqlGenerator sqlGenerator() {
		return new PostgreSqlGenerator();
	}

	@Bean
	@Override
	public DataSource dataSource() {
		PoolingDataSource ds = new PoolingDataSource();
		ds.setUser("postgres");
		ds.setPassword(System.getProperty("postgresql.password"));
		ds.setDatabaseName("spring_data_jdbc_repository_test");
		return ds;
	}

}