package com.ideas.survey.endpoint;

import com.ideas.survey.dto.ActiveDirectoryDAO;
import com.ideas.survey.dto.Data;
import com.ideas.survey.dto.EmployeeSurveyDetailsDTO;
import com.ideas.survey.entity.*;
import com.ideas.survey.service.ActiveDirectoryService;
import com.ideas.survey.service.EmpSurveyService;
import com.ideas.survey.service.MyRankingComp;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@RestController
@RequestMapping("/survey")
public class EmpSurveyController {

    @Autowired
    private AspectRepository aspectRepository;
    @Autowired
    private EmployeeSurveyRepository employeeSurveyRepository;
    @Autowired
    private FeedbackstatsRepository feedbackstatsRepository;

    @Autowired
    private EmpSurveyService empSurveyService;
    @Autowired
    private ActiveDirectoryService activeDirectoryService;
    @Autowired
    JdbcTemplate jdbcTemplate;
    ActiveDirectoryDAO activeDirectoryDAO = new ActiveDirectoryDAO();

    @RequestMapping("/getName")
    public String getUserName(HttpServletRequest request){


        String username = request.getUserPrincipal().getName();
        int pSlash = username.indexOf('\\');
        if (pSlash > 0) {

            username = username.substring(pSlash+1);
        }
        return  activeDirectoryDAO.getUserName("sAMAccountName",username);
    }
    @RequestMapping("/directReports")
    public ArrayList getDirectReports(@RequestParam("searchfield") String searchfield, @RequestParam("searchvalue") String accountName) {
       return activeDirectoryService.getDirectReportsfromDirectory(searchfield,accountName);
    }

    @RequestMapping("/empID")
    public String getEmployeeID(@RequestParam("searchfield") String searchfield, @RequestParam("searchvalue") String accountName) {
       return  activeDirectoryService.getEmployeeIDfromDirectory(searchfield,accountName);
    }

    @RequestMapping("/getData")

    public List<Data> getDataEntries(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return activeDirectoryService.getDataEntriesfromDirectory(request,response);
    }


    @RequestMapping("/getDataofindividualmanager")
    public List<Data> getDataEntriesofIndividualManager( @RequestParam("cn")String employeename, HttpServletRequest request,HttpServletResponse  response) throws  IOException{
        ArrayList<String> empids = new ArrayList<>();
        ArrayList<String> directreports = new ArrayList<>();
        directreports.add(employeename);
        if(activeDirectoryDAO.getDirectReports("cn", employeename)!=null)
        directreports.addAll(activeDirectoryService.getDirectReportsfromDirectory("cn", employeename)) ;

        for (String s : directreports)
            empids.add(activeDirectoryService.getEmployeeIDfromDirectory("cn", s));
        List<Data> DataEntries = new ArrayList<>();
        for (String s : empids) {
            jdbcTemplate.query("SELECT empid AS sas_id ,survey_id,aspect AS parameter,aspect_rating AS satisfaction,aspect_ranking AS importance,DATE_FORMAT(submission_date , '%d-%m-%Y')AS period FROM `employee_feedback` t1\n" +
                    " INNER JOIN `employee_feedback_stats` t2 USING (survey_id)\n" +
                    " INNER JOIN `aspects` USING (aspect_id) where empid = ?   AND t1.submission_date!=\"NULL\" ORDER BY survey_id;", new Object[]{s}, new RowMapper<Data>() {


                @Override
                public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Data data = new Data();
                    data.setSasid(rs.getString(1));
                    data.setSurveyid(rs.getInt(2));
                    data.setParameter(rs.getNString(3));
                    data.setSatisfaction(rs.getInt(4));
                    data.setImportance(rs.getInt(5));
                    data.setPeriod(rs.getNString(6));
                    DataEntries.add(data);
                    return data;
                }

            });
        }
        ActiveDirectoryDAO activeDirectoryDAO = new ActiveDirectoryDAO();

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + employeename + "_reports.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(
                " Employee Info ");

        XSSFRow row;
        int rowid = 0;
        int headid = 0;
        row = spreadsheet.createRow(rowid++);
        Cell col8 = row.createCell(headid++);
        col8.setCellValue("sas id");

        Cell col9 = row.createCell(headid++);
        col9.setCellValue("Designation");
        Cell col7 = row.createCell(headid++);
        col7.setCellValue("Manager");
        Cell col0 = row.createCell(headid++);
        col0.setCellValue("Department");

        Cell col1 = row.createCell(headid++);
        col1.setCellValue("survey id ");
        Cell col2 = row.createCell(headid++);
        col2.setCellValue("Parameter");
        Cell col3 = row.createCell(headid++);
        col3.setCellValue("satisfaction");
        Cell col4 = row.createCell(headid++);
        col4.setCellValue("importance");
        Cell col5 = row.createCell(headid++);
        col5.setCellValue("period");
        for (Data entry : DataEntries) {
            row = spreadsheet.createRow(rowid++);
            int cellid = 0;
            Cell cell = row.createCell(cellid++);
            cell.setCellValue(entry.getSasid());
            Cell cell6 = row.createCell(cellid++);
            cell6.setCellValue(activeDirectoryDAO.getDesignation("employeeID", entry.getSasid()));
            Cell cell7 = row.createCell(cellid++);
            cell7.setCellValue(activeDirectoryDAO.getManager("employeeID", entry.getSasid()));

            Cell cell8 = row.createCell(cellid++);
            cell8.setCellValue(activeDirectoryDAO.getDepartment("employeeID", entry.getSasid()));
            Cell cell1 = row.createCell(cellid++);
            cell1.setCellValue(entry.getSurveyid());
            Cell cell2 = row.createCell(cellid++);
            cell2.setCellValue(entry.getParameter());
            Cell cell3 = row.createCell(cellid++);
            cell3.setCellValue(entry.getSatisfaction());
            Cell cell4 = row.createCell(cellid++);
            cell4.setCellValue(entry.getImportance());
            Cell cell5 = row.createCell(cellid++);
            cell5.setCellValue(entry.getPeriod());


        }

        workbook.write(response.getOutputStream());
        return DataEntries;
    }

    @RequestMapping("/setTime")
    public void setTime(@RequestParam("empID") String empid, @RequestParam("surveyID") String surveyID) {

       empSurveyService.setTime(empid, surveyID);

    }

    @RequestMapping("/updateRating")
    public void updateRating(@RequestParam("empID") String empid, @RequestParam("surveyID") Integer surveyID, @RequestParam("aspectID") Integer aspectID, @RequestParam("rating") Integer rating) {
       empSurveyService.updateRating(empid,surveyID,aspectID,rating);
    }

    @RequestMapping("/updateRanking")
    public void updateRanking(@RequestParam("empID") String empid, @RequestParam("surveyID") Integer surveyID, @RequestParam("aspectID") Integer aspectID, @RequestParam("ranking") Integer ranking) {
     empSurveyService.updateRanking(empid, surveyID, aspectID, ranking);
    }

    @RequestMapping("/updateReason")
    public void updateReason(@RequestParam("empID") String empid, @RequestParam("surveyID") Integer surveyID, @RequestParam("aspectID") Integer aspectID, @RequestParam("reason") String reason) {
       empSurveyService.updateReason(empid, surveyID, aspectID, reason);
    }
    @RequestMapping("/saveEmpSurvey")
    public EmployeeSurvey saveEmployeeSurvey(@RequestParam("empID") String empid) {
        return empSurveyService.saveEmployeeSurvey(empid);
    }

    @RequestMapping("/getSurvey")
    public @ResponseBody List<EmployeeSurveyDetailsDTO> getEmployeeSurvey(@RequestParam("empID") String empid) {

       return  empSurveyService.getEmployeeSurvey(empid);
    }

    @RequestMapping("/getNewSurvey")
    public List<EmployeeSurveyDetailsDTO> getNewSurvey(@RequestParam("empID") String empid) {
      return empSurveyService.getNewSurvey(empid);

    }

    @RequestMapping("/getAllSurveys")
    public List<List<EmployeeSurveyDetailsDTO>> getAllSurveysofEmployee(@RequestParam("empID") String empid) {
        return   empSurveyService.getAllSurveysofEmployee(empid);
    }

}