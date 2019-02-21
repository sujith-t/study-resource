package aix.study.res;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Sujith T
 * 
 * <!In God We Trust>
 */
@ComponentScan(basePackages = {"aix.study.res.restcontroller", "aix.study.res.service"})
@EntityScan(basePackages={"aix.study.res.model"})
@EnableMongoRepositories({"aix.study.res.dao.nosql"})
@SpringBootApplication
public class ResourceApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}
}
