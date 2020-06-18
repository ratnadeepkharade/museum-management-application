package com.application.museummanagementbackend.repository;

import com.application.museummanagementbackend.model.Summary;
import com.application.museummanagementbackend.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SummaryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Summary> getAllSummary() {
        String sql = "SELECT * FROM addsummary";
        //String sql ="SELECT visitors.visitorID,visitors.firstName,visitors.lastName,visitors.gender,visitors.age,visitors.category,visitors.entryDate,visitors.sectionId,section.sectionName " +
          //      "FROM visitors " +
           //     "INNER JOIN section ON section.sectionID=visitors.sectionID";

        List<Summary> summarys = (List<Summary>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(Summary.class));

        return summarys;
    }


}
