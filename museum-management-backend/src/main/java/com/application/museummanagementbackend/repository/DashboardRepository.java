package com.application.museummanagementbackend.repository;

import com.application.museummanagementbackend.model.MonthlyCount;
import com.application.museummanagementbackend.model.TopSection;
import com.application.museummanagementbackend.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DashboardRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    SimpleJdbcCall simpleJdbcCallRefCursor;

    public List<Visitor> getAllVisitors() {
        //String sql = "SELECT * FROM visitors";
        String sql ="SELECT visitors.visitorID,visitors.firstName,visitors.lastName,visitors.gender,visitors.age,visitors.category,visitors.entryDate,visitors.sectionId,section.sectionName " +
                "FROM visitors " +
                "INNER JOIN section ON section.sectionID=visitors.sectionID";

        List<Visitor> visitors = (List<Visitor>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(Visitor.class));

        return visitors;
    }

    public int getCount(int type) {
        simpleJdbcCallRefCursor = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getCounts")
                .returningResultSet("out_count",
                        BeanPropertyRowMapper.newInstance(Integer.class));

        SqlParameterSource paramaters = new MapSqlParameterSource()
                .addValue("in_type", type);

        Map out = simpleJdbcCallRefCursor.execute(paramaters);

        if (out == null) {
            return 0;
        } else {
            return (Integer) out.get("out_count");
        }
    }

    public List<MonthlyCount> getMonthlyCount() {
        String sql ="SELECT Month(entryDate) as monthId, Count(*) as count " +
                "    FROM Visitors_Global " +
                "    WHERE entryDate >= CURDATE() - INTERVAL 1 YEAR " +
                "    GROUP BY Month(entryDate)";

        List<MonthlyCount> monthlyCounts = (List<MonthlyCount>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(MonthlyCount.class));

        return monthlyCounts;
    }

    public List<TopSection> getTopSections() {
        /*String sql ="SELECT Month(entryDate) as monthId, Count(*) as count " +
                "    FROM Visitors_Global " +
                "    WHERE entryDate >= CURDATE() - INTERVAL 1 YEAR " +
                "    GROUP BY Month(entryDate)";*/
        String sql ="select sectionName, count(*) as count " +
                "from Visitors_Global " +
                "group by sectionName " +
                "HAVING sectionName <> 'Main' " +
                "order by count desc " +
                "limit 5";

        List<TopSection> topSections = (List<TopSection>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(TopSection.class));

        return topSections;
    }
}
