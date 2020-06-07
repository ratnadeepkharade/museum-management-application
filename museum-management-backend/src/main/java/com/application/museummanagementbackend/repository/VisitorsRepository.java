package com.application.museummanagementbackend.repository;

import com.application.museummanagementbackend.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisitorsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Visitor> getAllVisitors() {
        String sql = "SELECT * FROM visitors";

        List<Visitor> visitors = (List<Visitor>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Visitor.class));

        return visitors;

    }
}
