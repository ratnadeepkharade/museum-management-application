package com.application.museummanagementbackend;

import com.application.museummanagementbackend.model.Customer;
import com.application.museummanagementbackend.model.Visitor;
import com.application.museummanagementbackend.repository.CustomerRepository;
import com.application.museummanagementbackend.repository.VisitorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MuseumManagementBackendApplication {
	private static final Logger log = LoggerFactory.getLogger(MuseumManagementBackendApplication.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MuseumManagementBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			log.info("StartApplication...");

			startCustomerApp();

		};
	}

	@Autowired
	VisitorsRepository visitorsRepository;

	void startCustomerApp() {

		//jdbcTemplate.execute("DROP TABLE IF EXISTS customer");
		//jdbcTemplate.execute("CREATE TABLE customer(" +
		//		"id SERIAL, name VARCHAR(255), age NUMERIC(2), created_date timestamp)");

		jdbcTemplate.execute("drop table if exists Visitors");
		jdbcTemplate.execute("CREATE TABLE Visitors (" +
				"visitorId INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
				"firstName VARCHAR(255)," +
				"lastName VARCHAR(255)," +
				"gender VARCHAR(255)," +
				"age INT," +
				"category VARCHAR(255)," +
				"sectionId INT" +
				")");

		/*List<Customer> list = Arrays.asList(
				new Customer("Customer A", 19),
				new Customer("Customer B", 20),
				new Customer("Customer C", 21),
				new Customer("Customer D", 22)
		);
		list.forEach(x -> {
			log.info("Saving...{}", x.getName());
			customerRepository.save(x);
		});*/

		List<Visitor> visitorslist = Arrays.asList(
				new Visitor("Bradd", "Pitt", "Male", 60, "Full day", 123),
				new Visitor("Albert", "Einstein", "Male", 60, "Full day", 163),
				new Visitor("Ratnadeep", "Kharade", "Male", 60, "Full day", 1253)
		);

		visitorslist.forEach(x -> {
			log.info("Saving...{}", x.getFirstName());
			visitorsRepository.save(x);
		});



	}

}
