package jihye.backend_mock_exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BackendMockExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendMockExamApplication.class, args);
	}

}
