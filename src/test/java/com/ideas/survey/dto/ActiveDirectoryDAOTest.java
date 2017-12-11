package com.ideas.survey.dto;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by idnvlr on 9/27/2017.
 */
public class ActiveDirectoryDAOTest {

    private ActiveDirectoryDAO activeDirectoryDAO;

    @Test
    public void findDirectReports(){
        activeDirectoryDAO = new ActiveDirectoryDAO();
      activeDirectoryDAO.getDirectReports("CN", "Prasad Kunte");
    }

    @Test
    public void getEmpIdofEmployee(){
        ActiveDirectoryDAO dao = new ActiveDirectoryDAO();
        System.out.println(dao.getEmpID("sAMAccountName", "idnasr"));
    }
}