package com.application.museummanagementbackend;

import com.application.museummanagementbackend.model.Section;
import com.application.museummanagementbackend.model.Visitor;
import com.application.museummanagementbackend.repository.SectionsRepository;
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
    @Autowired
    SectionsRepository sectionsRepository;

    void startCustomerApp() {
        jdbcTemplate.execute("drop table if exists Visitors");

		jdbcTemplate.execute("drop table if exists Sections");
		jdbcTemplate.execute("CREATE TABLE Sections (" +
				"sectionId BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
				"sectionName VARCHAR(255)" +
				")"
		);

        List<Section> sectionlist = Arrays.asList(
                new Section("Main"),
                new Section("Art Gallery"),
                new Section("Natural Histroy"),
                new Section("Ancient Architecture")
        );

        sectionlist.forEach(x -> {
            log.info("Saving...{}", x.getSectionName());
            sectionsRepository.saveSection(x);
        });

       	jdbcTemplate.execute("CREATE TABLE Visitors (" +
                "visitorId BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "firstName VARCHAR(255)," +
                "lastName VARCHAR(255)," +
                "gender VARCHAR(10)," +
                "age INT," +
                "category VARCHAR(255)," +
			   	"entryDate BIGINT," +
                "sectionId BIGINT, " +
                "CONSTRAINT fk_section" +
                "  FOREIGN KEY (sectionId)" +
                "  REFERENCES sections(sectionId)"+
			   	//"FOREIGN KEY (sectionId) REFERENCES sections(sectionId)"+
                ")"
	   );

        List<Visitor> visitorslist = Arrays.asList(
                new Visitor("Brad", "Pitt", "Male", 60, "Daily", 1),
                new Visitor("Albert", "Einstein", "Male", 60, "Daily", 1),
                new Visitor("Ratnadeep", "Kharade", "Male", 60, "Daily", 1)
        );

        visitorslist.forEach(x -> {
            log.info("Saving...{}", x.getFirstName());
            visitorsRepository.save(x);
        });

    }

}
