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
        jdbcTemplate.execute("drop table if exists user");
        jdbcTemplate.execute("drop table if exists Visitors");
        jdbcTemplate.execute("drop table if exists artifacts");
        jdbcTemplate.execute("drop table if exists addsummary");
        jdbcTemplate.execute("drop table if exists Employee");
        jdbcTemplate.execute("DROP VIEW IF EXISTS employeewithsectionName");
        jdbcTemplate.execute("drop table if exists Section");

        // SQL for user table
        jdbcTemplate.execute("CREATE TABLE user (" +
                "        id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, " +
                "        firstName VARCHAR(30) NOT NULL, " +
                "        lastName VARCHAR(30) NOT NULL " +
                "        )");

        jdbcTemplate.execute("INSERT INTO user (firstName, lastName) VALUES ('John', 'Doe')");


        // SQL for Visitors_Global table(Data Warehouse)
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


        // SQL for section table
		jdbcTemplate.execute("CREATE TABLE Section (" +
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


        // SQL for visitors table and stored procedures and triggers on visitors table
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
                "  REFERENCES section(sectionId)"+
			   	//"FOREIGN KEY (sectionId) REFERENCES section(sectionId)"+
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
                "SELECT sectionName INTO secName FROM Section WHERE sectionId=new.sectionId;" +
                "INSERT INTO Visitors_Global  VALUES(new.visitorId, new.firstName, new.lastName, new.gender, new.age, new.category, entDate, 0, new.sectionId, secName);" +
                "END"
        );

        jdbcTemplate.execute("DROP TRIGGER IF EXISTS update_visitor_in_global");
        jdbcTemplate.execute("CREATE TRIGGER update_visitor_in_global " +
                "AFTER DELETE ON Visitors " +
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
                "SELECT COUNT(*) INTO out_count FROM Artifacts; " +
                "WHEN in_type = 4 THEN " +
                "SELECT COUNT(*) INTO out_count FROM Section; " +
                "ELSE " +
                "SELECT COUNT(*) INTO out_count FROM Visitors_Global; " +
                "END CASE; " +
                "END";
        jdbcTemplate.execute("DROP PROCEDURE IF EXISTS getCounts");
        jdbcTemplate.execute(procedureSql);


        // SQL for Employee table
        jdbcTemplate.execute("CREATE TABLE Employee ( " +
                "empId int  NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "lastName varchar(255) NOT NULL, " +
                "firstName varchar(255), " +
                "emailId varchar(255), " +
                "roleName  varchar(255), " +
                "sectionId BIGINT " +
                ")"
        );
        jdbcTemplate.execute("ALTER TABLE Employee ADD CONSTRAINT FK_EmployeeSection FOREIGN KEY (sectionId) REFERENCES section(sectionId)");
        jdbcTemplate.execute("insert into Employee values(null,'John','Doe','john.doe@gmail.com','Graphic Designer',2)");
        jdbcTemplate.execute("insert into Employee values(null,'Jane','Doe','jane.doe@gmail.com','Manager',2)");
        jdbcTemplate.execute("CREATE VIEW employeewithsectionName AS SELECT e.empId, e.lastName,e.firstName,e.emailId,e.roleName,s.sectionName FROM employee e,section s WHERE s.sectionId=e.sectionId");


        // SQL for artifacts table
        jdbcTemplate.execute("CREATE TABLE artifacts ( " +
                "artifactsID bigint NOT NULL AUTO_INCREMENT, " +
                "artifactName varchar(255) NOT NULL, " +
                "dateArrived bigint NOT NULL, " +
                "artifactType varchar(255) NOT NULL, " +
                "sectionid BIGINT NOT NULL, " +
                "empid int NOT NULL, " +
                "amount int NOT NULL, " +
                "acquiredFrom varchar(20) NOT NULL, " +
                "quantity int NOT NULL, " +
                "PRIMARY KEY (artifactsID), " +
                "KEY empId_idx (empid), " +
                "KEY sectionid_idx (sectionid), " +
                "CONSTRAINT empId FOREIGN KEY (empid) REFERENCES employee (empId), " +
                "CONSTRAINT sectionid FOREIGN KEY (sectionid) REFERENCES section (sectionId) " +
                ")"
        );
        jdbcTemplate.execute("INSERT INTO `artifacts` VALUES (1,'RRVPainting',1592303236852,'Painting',1,1,40000,'ABC',0)");
        jdbcTemplate.execute("INSERT INTO `artifacts` VALUES (2,'RRVPainting',1592303236852,'Painting1',2,1,40000,'ABC',0)");

        jdbcTemplate.execute("CREATE TABLE addsummary ( " +
                "sectionid bigint NOT NULL, " +
                "sectionName varchar(45) NOT NULL, " +
                "artifactCount int NOT NULL, " +
                "TotalAmount bigint NOT NULL, " +

                ")"
        );
        jdbcTemplate.execute(	"CREATE TRIGGER artifacts_AFTER_INSERT"+
                                "AFTER INSERT ON artifacts"+
                                "FOR EACH ROW"+
                                "BEGIN"+
                                "update addsummary set artifactCount = artifactCount+NEW.quantity WHERE addsummary.sectionid = NEW.sectionid ;"+
                                "update addsummary set TotalAmount = TotalAmount+NEW.amount WHERE addsummary.sectionid = NEW.sectionid ;"+
                                 "END"

        );
        jdbcTemplate.execute("CREATE TRIGGER artifacts_AFTER_DELETE"+
                                 " AFTER DELETE ON artifacts"+
                                  "FOR EACH ROW"+
                                    "BEGIN"+
                                   "update addsummary set artifactCount = artifactCount-OLD.quantity WHERE addsummary.sectionid = OLD.sectionid ;"+
                                   "update addsummary set TotalAmount = TotalAmount-OLD.amount WHERE addsummary.sectionid = OLD.sectionid ;"+
                                  "END"

        );



    }

}
