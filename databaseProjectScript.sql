show databases;

create database sample;

use sample;

show tables;

CREATE TABLE user (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
firstName VARCHAR(30) NOT NULL,
lastName VARCHAR(30) NOT NULL
);

INSERT INTO user (firstName, lastName)
VALUES ("John", "Doe");

select * from Employee;

delete from employee where lastname='Upadhye';
commit;

CREATE TABLE Employee (
    empld int  NOT NULL PRIMARY KEY,
    lastName varchar(255) NOT NULL,
    firstName varchar(255),
    emailId varchar(255),
    dateOfBirth date,
    roleName  varchar(255),
    sectionId int
);
INSERT INTO employee (sectionId)
VALUES (2);
insert into Employee values(2,'Upadhye','priyanka','priyanka.upadhye@gmail.com','1992-06-12','Graphic Designer',2);
commit;


CREATE TABLE section (
sectionId INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
sectionName VARCHAR(30) NOT NULL
);


Insert into section values(2,'Moder Art');

ALTER TABLE Employee
ADD CONSTRAINT FK_EmployeeSection
FOREIGN KEY (sectionId) REFERENCES section(sectionId);
select * from section;

CREATE VIEW employeewithsectionName AS
SELECT e.lastName,e.firstName,e.emailId,e.roleName,s.sectionName
FROM employee e,section s
WHERE s.sectionId=e.sectionId;