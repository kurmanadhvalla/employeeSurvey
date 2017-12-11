package com.ideas.survey.service;

import com.ideas.survey.dto.ActiveDirectoryDAO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by idnvlr on 10/24/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActiveDirectoryServiceTest {
    private String searchField= "cn";
    private String emplName="Prasad Kunte";
    @Mock
    private ActiveDirectoryDAO activeDirectoryDAO;
   @InjectMocks
    private  ActiveDirectoryService activeDirectoryService;
    @Test
    public void  findDirectReports(){
        activeDirectoryService.getDirectReportsfromDirectory(searchField, emplName);
        verify(activeDirectoryDAO).getDirectReports(searchField,emplName );

    }

    @Test
    public void findEmpID(){
        activeDirectoryService.getEmployeeIDfromDirectory(searchField, emplName);
        verify(activeDirectoryDAO).getEmpID(searchField,emplName );
    }
    


}