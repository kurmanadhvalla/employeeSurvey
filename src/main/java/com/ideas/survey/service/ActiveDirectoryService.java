package com.ideas.survey.service;

import com.ideas.survey.dto.ActiveDirectoryDAO;
import com.ideas.survey.dto.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by idnvlr on 10/24/2017.
 */

@Component
public class ActiveDirectoryService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ArrayList getDirectReportsfromDirectory(@RequestParam("searchfield") String searchfield, @RequestParam("searchvalue") String accountName) {
        ActiveDirectoryDAO active = new ActiveDirectoryDAO();
        return active.getDirectReports(searchfield, accountName);
    }

    public String getEmployeeIDfromDirectory(@RequestParam("searchfield") String searchfield, @RequestParam("searchvalue") String accountName) {
        ActiveDirectoryDAO activeDirectoryDAO = new ActiveDirectoryDAO();
        return activeDirectoryDAO.getEmpID(searchfield, accountName);
    }

    public List<Data> getDataEntriesfromDirectory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Data> dataEntries = new ArrayList<>();
        jdbcTemplate.query("SELECT empid AS sas_id ,survey_id,aspect AS parameter,aspect_rating AS satisfaction,aspect_ranking AS importance,DATE_FORMAT(submission_date , '%d-%m-%Y')AS period FROM `employee_feedback` t1\n" +
                " INNER JOIN `employee_feedback_stats` t2 USING (survey_id)\n" +
                " INNER JOIN `aspects` USING (aspect_id)  ORDER BY survey_id;", new RowMapper<Data>() {


            @Override
            public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
                Data data = new Data();
                data.setSasid(rs.getString(1));
                data.setSurveyid(rs.getInt(2));
                data.setParameter(rs.getNString(3));
                data.setSatisfaction(rs.getInt(4));
                data.setImportance(rs.getInt(5));
                data.setPeriod(rs.getNString(6));
                dataEntries.add(data);
                return data;
            }

        });
        ActiveDirectoryDAO activeDirectoryDAO = new ActiveDirectoryDAO();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");
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
        for (Data entry : dataEntries) {
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
//    out.close();

        return dataEntries;
    }
}
