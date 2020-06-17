package com.application.museummanagementbackend.repository;

import com.application.museummanagementbackend.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VisitorsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Visitor> getAllVisitors() {
        //String sql = "SELECT * FROM visitors";
        String sql ="SELECT visitors.visitorID,visitors.firstName,visitors.lastName,visitors.gender,visitors.age,visitors.category,visitors.entryDate,visitors.sectionId,section.sectionName " +
                "FROM visitors " +
                "INNER JOIN section ON section.sectionID=visitors.sectionID";

        List<Visitor> visitors = (List<Visitor>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(Visitor.class));

        return visitors;
    }

    public int save(Visitor visitor) {
        return jdbcTemplate.update(
            "insert into visitors (firstName, lastName, gender, age, category, sectionId, entryDate) values(?,?,?,?,?,?,?)",
            visitor.getFirstName(), visitor.getLastName(), visitor.getGender(), visitor.getAge(), visitor.getCategory(),visitor.getSectionId(), System.currentTimeMillis());
    }

    public int update(Visitor visitor) {
        String sqlString = "update visitors set firstName= ?, lastName = ?, gender = ?, age = ?, category = ?, sectionId = ? where visitorId = ?";
        return jdbcTemplate.update(sqlString, visitor.getFirstName(), visitor.getLastName(), visitor.getGender(), visitor.getAge(), visitor.getCategory(),visitor.getSectionId(), visitor.getVisitorId());
    }

    public int deleteVisitor(int visitorId) {
        String sql = "delete  FROM visitors where visitorId=?";
        return jdbcTemplate.update(sql,visitorId);
    }

    public int getVisitorsCount(){
        String sql = "SELECT COUNT(*) FROM count_demos";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
