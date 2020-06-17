package com.application.museummanagementbackend.repository;

import com.application.museummanagementbackend.model.Section;
import com.application.museummanagementbackend.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SectionsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Section> getAllSections() {
        String sql = "SELECT * FROM section";

        List<Section> sections = (List<Section>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(Section.class));

        return sections;
    }

    public int saveSection(Section section) {
        return jdbcTemplate.update(
                "insert into section (sectionName) values(?)",
                section.getSectionName());
    }

    public int updateSection(Section section) {
        String sqlString = "update section set sectionName= ? where sectionId = ?";
        return jdbcTemplate.update(sqlString, section.getSectionName(), section.getSectionId());
    }

    public int deleteSection(int sectionId) {
        String sql = "delete  FROM section where sectionId=?";
        return jdbcTemplate.update(sql,sectionId);
    }
}
