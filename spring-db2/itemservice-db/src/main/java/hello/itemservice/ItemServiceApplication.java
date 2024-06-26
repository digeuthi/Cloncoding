package hello.itemservice;

import hello.itemservice.config.*;
import hello.itemservice.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


//@Import(MemoryConfig.class)
//@Import(JdbcTemplateV1Config.class)
//@Import(JdbcTemplateV2Config.class)
@Import(JdbcTemplateV3Config.class)
@SpringBootApplication(scanBasePackages = "hello.itemservice.web")
@Slf4j
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	@Bean
	@Profile("local")
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		return new TestDataInit(itemRepository);
	}

	//Test에서만 사용하는 임베디드모드
	/*@Bean
	@Profile("test")
	public DataSource dataSource(){
		//프로필이 test인 경우에만 dataSource를 직접 등록
		log.info("메모리 데이터베이스 초기화");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver"); //h2 데이터베이스 드라이버 지정
		dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1"); //JVM내부에 DB를 만들고 메모리(mem)로 드라이버띄우기
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}*/

}
