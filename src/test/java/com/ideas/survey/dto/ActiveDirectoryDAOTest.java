package com.ideas.survey.dto;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by idnvlr on 9/27/2017.
 */
public class ActiveDirectoryDAOTest {

    @Test
    public void findDirectReports(){
        ActiveDirectoryDAO dao = new ActiveDirectoryDAO();
      dao.getDirectReports("CN", "Prasad Kunte");
    }

    @Test
    public void getEmpIdofEmployee(){
        ActiveDirectoryDAO dao = new ActiveDirectoryDAO();
        System.out.println(dao.getEmpID("sAMAccountName", "idnasr"));
    }
}