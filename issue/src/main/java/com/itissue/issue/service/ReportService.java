package com.itissue.issue.service;

import com.itissue.issue.models.Issue;
import com.itissue.issue.repo.IssueRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private IssueRepo issueRepo;


    public String exportReport(String reportFormat) throws JRException, FileNotFoundException {
        String path = "C:\\Users\\ridea\\Desktop\\Report";
        List<Issue> issues = (List<Issue>) issueRepo.findAll();
        File file= ResourceUtils.getFile("classpath:Issue.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(issues);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("createBy","Oleg");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\issue.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\issue.pdf");
        }
        return "report generated in path : " + path;


    }
}
