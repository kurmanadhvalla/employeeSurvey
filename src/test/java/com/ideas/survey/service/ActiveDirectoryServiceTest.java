package com.ideas.survey.service;

import com.ideas.survey.dto.ActiveDirectoryDAO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by idnvlr on 10/24/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActiveDirectoryServiceTest {
    ActiveDirectoryService  activeDirectoryService = new ActiveDirectoryService();
    @Test
    public void  findDirectReports(){

        System.out.println(activeDirectoryService.getDirectReportsfromDirectory("CN","Prasad Kunte")) ;

    }

    @Test
    public void findEmpID(){
        assertEquals("E81065",activeDirectoryService.getEmployeeIDfromDirectory("CN","Prasad Kunte"));
    }
    


}