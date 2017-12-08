package com.ideas.survey.dto;


import com4j.COM4J;
import com4j.typelibs.activeDirectory.IADs;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com4j.typelibs.ado20._Command;
import com4j.typelibs.ado20._Connection;
import com4j.typelibs.ado20._Recordset;

import com4j.ComException;
import com4j.Variant;
import com4j.typelibs.ado20.ClassFactory;
import com4j.typelibs.ado20.Fields;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActiveDirectoryDAO {

    private Object x;

    static Object[] convertToObjectArray(Object array) {
        Class ofArray = array.getClass().getComponentType();
        if (ofArray.isPrimitive()) {
            List ar = new ArrayList();
            int length = Array.getLength(array);
            for (int i = 0; i < length; i++) {
                ar.add(Array.get(array, i));
            }
            return ar.toArray();
        }
        else {
            return (Object[]) array;
        }
    }



    protected Log _log = LogFactory.getLog(ActiveDirectoryDAO.class);
    static String defaultNamingContext = null;
    static final String usefulFields = "cn,distinguishedName,userPrincipalName,sAMAccountName,sn,givenName,title,mail,manager,employeeID,Department,company,division,thumbnailPhoto,thumbnailLogo,memberOf,directReports";
    synchronized void initNamingContext() {
        if (defaultNamingContext == null) {
            IADs rootDSE = COM4J.getObject(IADs.class, "LDAP://RootDSE", null);
            defaultNamingContext = (String)rootDSE.get("defaultNamingContext");
        }
    }

    public  ArrayList<String>  getDirectReports (String searchField, String searchValue) {

        EmployeeSurveyDetailsDTO employeeSurveyDetailsDTO = new EmployeeSurveyDetailsDTO();
        initNamingContext();

        if (defaultNamingContext == null) {
            _log.error("Could not initialize default naming context");
            return null;
        }

        // Searching LDAP requires ADO [8], so it's good to create a connection upfront for reuse.

        _Connection con = ClassFactory.createConnection();
        con.provider("ADsDSOObject");
        con.open("Active Directory Provider",""/*default*/,""/*default*/,-1/*default*/);

        // query LDAP to find out the LDAP DN and other info for the given user from the login ID

        _Command cmd = ClassFactory.createCommand();
        cmd.activeConnection(con);


        //_log.info("Command="+"<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
        cmd.commandText("<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
        _Recordset rs = cmd.execute(null, Variant.getMissing(), -1/*default*/);

        if(rs.eof()) { // User not found!
            _log.error(searchValue+" not found.");
        }
        else {
            Fields userData = rs.fields();
            if (userData != null) {
               Object o;
                try {
                    o =  userData.item("directReports").value();
                    if (o != null) {
                        ArrayList  reports = new ArrayList (Arrays.asList(convertToObjectArray(o)));
//
                        ArrayList<String> directreports = new ArrayList<>();
                        for(int i=0;i<reports.size();i++) {
                            String str = reports.get(i).toString();
                            List<String> items = Arrays.asList(str.split("\\s*,\\s*"));;
                            for (int j = 0; j < items.size(); j++) {
                                if (items.get(j).contains("CN="))
                                    directreports.add(items.get(j).substring(3,items.get(j).length()));
                            }
                        }
                        return directreports;
                    }
                } catch (ComException ecom ) {
                    _log.error("manager not returned:"+ecom.getMessage());
                }

            }
            else {
                _log.error("User "+searchValue+" information is empty?");
            }
        }
        rs.close();
        con.close();
        return null ;
    }

    public String getEmpID (String searchField, String searchValue) {
        initNamingContext();
        if (defaultNamingContext == null) {
            _log.error("Could not initialize default naming context");
            return null;
        }

        // Searching LDAP requires ADO [8], so it's good to create a connection upfront for reuse.

        _Connection con = ClassFactory.createConnection();
        con.provider("ADsDSOObject");
        con.open("Active Directory Provider",""/*default*/,""/*default*/,-1/*default*/);

        // query LDAP to find out the LDAP DN and other info for the given user from the login ID

        _Command cmd = ClassFactory.createCommand();
        cmd.activeConnection(con);

        //_log.info("Command="+"<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
        cmd.commandText("<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
        _Recordset rs = cmd.execute(null, Variant.getMissing(), -1/*default*/);

        if(rs.eof()) { // User not found!
            _log.error(searchValue+" not found.");
        }
        else {
            Fields userData = rs.fields();
            if (userData != null) {
                Object o;
                try {
                    o = userData.item("employeeID").value();
                    if (o != null) return o.toString();
                } catch (ComException ecom ) {
                    _log.error("manager not returned:"+ecom.getMessage());
                }

            }
            else {
                _log.error("User "+searchValue+" information is empty?");
            }
        }
        rs.close();
        con.close();

        return "not_found";
    }



    public String getDesignation (String searchField, String searchValue) {
        initNamingContext();
        if (defaultNamingContext == null) {
            _log.error("Could not initialize default naming context");
            return null;
        }

        // Searching LDAP requires ADO [8], so it's good to create a connection upfront for reuse.

        _Connection con = ClassFactory.createConnection();
        con.provider("ADsDSOObject");
        con.open("Active Directory Provider",""/*default*/,""/*default*/,-1/*default*/);

        // query LDAP to find out the LDAP DN and other info for the given user from the login ID

        _Command cmd = ClassFactory.createCommand();
        cmd.activeConnection(con);

        //_log.info("Command="+"<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
        cmd.commandText("<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
        _Recordset rs = cmd.execute(null, Variant.getMissing(), -1/*default*/);

        if(rs.eof()) { // User not found!
            _log.error(searchValue+" not found.");
        }
        else {
            Fields userData = rs.fields();
            if (userData != null) {
                Object o;
                try {
                    o = userData.item("title").value();
                    if (o != null) return o.toString();
                } catch (ComException ecom ) {
                    _log.error("manager not returned:"+ecom.getMessage());
                }

            }
            else {
                _log.error("User "+searchValue+" information is empty?");
            }
        }
        rs.close();
        con.close();

        return "not_found";
    }

    public String getManager (String searchField, String searchValue) {
        initNamingContext();
        if (defaultNamingContext == null) {
            _log.error("Could not initialize default naming context");
            return null;
        }

        // Searching LDAP requires ADO [8], so it's good to create a connection upfront for reuse.

        _Connection con = ClassFactory.createConnection();
        con.provider("ADsDSOObject");
        con.open("Active Directory Provider",""/*default*/,""/*default*/,-1/*default*/);

        // query LDAP to find out the LDAP DN and other info for the given user from the login ID

        _Command cmd = ClassFactory.createCommand();
        cmd.activeConnection(con);

        //_log.info("Command="+"<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
        cmd.commandText("<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
        _Recordset rs = cmd.execute(null, Variant.getMissing(), -1/*default*/);

        if(rs.eof()) { // User not found!
            _log.error(searchValue+" not found.");
        }
        else {
            Fields userData = rs.fields();
            if (userData != null) {
                Object o;
                try {
                    o = userData.item("manager").value();
                    if (o != null) {
                        String manager=o.toString();
                       String  managerName = null;
                        String[] split = manager.split(",");
                        for (String x : split) {
                            if (x.contains("CN=")) {
                                managerName = x.trim();
                            }
                        }
                        return managerName.substring(3);
                        }
                } catch (ComException ecom ) {
                    _log.error("manager not returned:"+ecom.getMessage());
                }

            }
            else {
                _log.error("User "+searchValue+" information is empty?");
            }
        }
        rs.close();
        con.close();

        return "not_found";
    }

    public String getDepartment (String searchField, String searchValue) {
        initNamingContext();
        if (defaultNamingContext == null) {
            _log.error("Could not initialize default naming context");
            return null;
        }

        // Searching LDAP requires ADO [8], so it's good to create a connection upfront for reuse.

        _Connection con = ClassFactory.createConnection();
        con.provider("ADsDSOObject");
        con.open("Active Directory Provider",""/*default*/,""/*default*/,-1/*default*/);

        // query LDAP to find out the LDAP DN and other info for the given user from the login ID

        _Command cmd = ClassFactory.createCommand();
        cmd.activeConnection(con);

        //_log.info("Command="+"<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
        cmd.commandText("<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
        _Recordset rs = cmd.execute(null, Variant.getMissing(), -1/*default*/);

        if(rs.eof()) { // User not found!
            _log.error(searchValue+" not found.");
        }
        else {
            Fields userData = rs.fields();
            if (userData != null) {
                Object o;
                try {
                    o = userData.item("Department").value();
                    if (o != null) return o.toString();
                } catch (ComException ecom ) {
                    _log.error("manager not returned:"+ecom.getMessage());
                }

            }
            else {
                _log.error("User "+searchValue+" information is empty?");
            }
        }
        rs.close();
        con.close();

        return "not_found";
    }

    public String getUserName (String searchField, String searchValue) {
        initNamingContext();
        if (defaultNamingContext == null) {
            _log.error("Could not initialize default naming context");
            return null;
        }

        // Searching LDAP requires ADO [8], so it's good to create a connection upfront for reuse.

        _Connection con = ClassFactory.createConnection();
        con.provider("ADsDSOObject");
        con.open("Active Directory Provider",""/*default*/,""/*default*/,-1/*default*/);

        // query LDAP to find out the LDAP DN and other info for the given user from the login ID

        _Command cmd = ClassFactory.createCommand();
        cmd.activeConnection(con);

        //_log.info("Command="+"<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
        cmd.commandText("<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
        _Recordset rs = cmd.execute(null, Variant.getMissing(), -1/*default*/);

        if(rs.eof()) { // User not found!
            _log.error(searchValue+" not found.");
        }
        else {
            Fields userData = rs.fields();
            if (userData != null) {
                Object o;
                try {
                    o = userData.item("cn").value();
                    if (o != null) return o.toString();
                } catch (ComException ecom ) {
                    _log.error("manager not returned:"+ecom.getMessage());
                }

            }
            else {
                _log.error("User "+searchValue+" information is empty?");
            }
        }
        rs.close();
        con.close();

        return "not_found";
    }
}
