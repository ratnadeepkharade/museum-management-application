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
        jdbcTemplate.execute("drop table if exists Visitors_Global");
        jdbcTemplate.execute("CREATE TABLE Visitors_Global (" +
                "visitorId BIGINT NOT NULL PRIMARY KEY," +
                "firstName VARCHAR(255)," +
                "lastName VARCHAR(255)," +
                "gender VARCHAR(10)," +
                "age INT," +
                "category VARCHAR(255)," +
                "entryDate DATETIME," +
                "exitDate DATETIME," +
                "sectionId BIGINT, " +
                "sectionName VARCHAR(255)" +
                ")"
        );
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

       	jdbcTemplate.execute("DROP TRIGGER IF EXISTS add_visitor_to_global");
        jdbcTemplate.execute("CREATE TRIGGER add_visitor_to_global " +
                "AFTER INSERT ON Visitors " +
                "FOR EACH ROW " +
                "BEGIN " +
                "Declare secName varchar(255);" +
                "Declare entDate DATETIME;" +
                "SELECT FROM_UNIXTIME(new.entryDate/1000) into entDate;" +
                "SELECT sectionName INTO secName FROM Sections WHERE sectionId=new.sectionId;" +
                "INSERT INTO Visitors_Global  VALUES(new.visitorId, new.firstName, new.lastName, new.gender, new.age, new.category, entDate, 0, new.sectionId, secName);" +
                "END"
        );

        jdbcTemplate.execute("DROP TRIGGER IF EXISTS update_visitor_in_global");
        jdbcTemplate.execute("CREATE TRIGGER update_visitor_in_global " +
                "BEFORE DELETE ON Visitors " +
                "FOR EACH ROW " +
                "BEGIN " +
                "UPDATE Visitors_Global SET exitDate=NOW() WHERE visitorId=OLD.visitorId;" +
                "END"
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

        String procedureSql = "CREATE PROCEDURE getCounts (" +
                "IN in_type INTEGER, " +
                "OUT out_count  INTEGER " +
                ") " +
                "BEGIN " +
                "CASE  " +
                "WHEN in_type = 1 THEN " +
                "SELECT COUNT(*) INTO out_count FROM Visitors; " +
                "WHEN in_type = 2 THEN " +
                "SELECT COUNT(*) INTO out_count FROM Visitors_Global; " +
                "WHEN in_type = 3 THEN " +
                "SELECT COUNT(*) INTO out_count FROM Sections; " +
                "ELSE " +
                "SELECT COUNT(*) INTO out_count FROM Visitors_Global; " +
                "END CASE; " +
                "END";
        jdbcTemplate.execute("DROP PROCEDURE IF EXISTS getCounts");
        jdbcTemplate.execute(procedureSql);

    }

}
