package com.application.museummanagementbackend.repository;

import com.application.museummanagementbackend.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    public List<Employee>  getAllEmployee() {
        String sql = "SELECT * FROM employeewithsectionName";

         List<Employee> employees = (List<Employee>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Employee.class));

        return employees;


    }
    public int deleteVisitor(int visitorId) {
        String sql = "delete  FROM visitors where visitorId=?";
        return jdbcTemplate.update(sql,visitorId);
    }
    public int  deleteEmployee(int empId) {
        String sql = "delete  FROM employee where empId=?";
        return jdbcTemplate.update(sql,empId);

    }/*
    public void  save(String lastName, String firstName, String emailId, Date dateOfBirth, String roleName, String sectionName) {
        SimpleJdbcCall jdbcCall = new
                SimpleJdbcCall(dataSource).withProcedureName("InsertintoEmployee");

        SqlParameterSource in = new MapSqlParameterSource().addValue("lastName",lastName).addValue("firstName",firstName).
                addValue("emailId",emailId).addValue("dateOfBirth",dateOfBirth).addValue("roleName",roleName).addValue("sectionName",sectionName);
        Map<String, Object> out = jdbcCall.execute(in);*/
    public int  save(Employee emp){
        return jdbcTemplate.update(
                "CALL InsertintoEmployee(?,?,?,?,?);",
                 emp.getLastName(),emp.getFirstName(), emp.getEmailId(),emp.getRoleName(),emp.getsectionName());
    }
    public int  update(Employee emp){
        return jdbcTemplate.update(
                "CALL UpdateEmployee(?,?,?,?,?,?);",
                emp.getEmpId(),emp.getLastName(),emp.getFirstName(), emp.getEmailId(),emp.getRoleName(),emp.getsectionName());
    }

}
