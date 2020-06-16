package com.application.museummanagementbackend.repository;

import com.application.museummanagementbackend.model.Artifact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ArtifactRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Artifact> getAllArtifact() {
        String sql ="SELECT artifacts.artifactsID,artifacts.artifactsID,artifacts.artifactName,artifacts.artifactType,artifacts.dateArrived,artifacts.sectionid,artifacts.empid,artifacts.amount,artifacts.acquiredFrom,artifacts.quantity,sections.sectionName,employee.firstName " +
                "FROM artifacts " +
                "INNER JOIN sections ON sections.sectionID=artifacts.sectionid " +
                "INNER JOIN employee ON employee.empId=artifacts.empid";


        List<Artifact> artifacts = (List<Artifact>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Artifact.class));

        return artifacts;

    }

    public int  deleteArtifact(int artifactsID) {
        String sql = "delete  FROM artifacts where artifactsID=?";
        return jdbcTemplate.update(sql,artifactsID);

    }/*
    public void  save(String lastName, String firstName, String emailId, Date dateOfBirth, String roleName, String sectionName) {
        SimpleJdbcCall jdbcCall = new
                SimpleJdbcCall(dataSource).withProcedureName("InsertintoEmployee");

        SqlParameterSource in = new MapSqlParameterSource().addValue("lastName",lastName).addValue("firstName",firstName).
                addValue("emailId",emailId).addValue("dateOfBirth",dateOfBirth).addValue("roleName",roleName).addValue("sectionName",sectionName);
        Map<String, Object> out = jdbcCall.execute(in);*/
    public int  save(Artifact arti){
        return jdbcTemplate.update(
                "insert into artifacts(artifactName,artifactType,dateArrived,sectionid,empid,amount,acquiredFrom,quantity) values (?,?,?,?,?,?,?,?)",
                arti.getartifactName(), arti.getartifactType(),System.currentTimeMillis(),arti.getsectionid(),arti.getempid(),arti.getamount(), arti.getacquiredFrom(), arti.getquantity());

    }

    public int  update(Artifact arti){

        String sqlString = "update artifacts set artifactsID=?,artifactName=?,artifactType=?,dateArrived=?,sectionid=?,empid=?,amount=?,acquiredFrom=?,quantity=?";
        return jdbcTemplate.update(sqlString,  arti.getartifactsID(),arti.getartifactName(), arti.getartifactType(),System.currentTimeMillis(),arti.getsectionid(),arti.getempid(),arti.getamount(), arti.getacquiredFrom(), arti.getquantity());
    }

}
