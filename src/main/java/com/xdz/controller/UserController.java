package com.xdz.controller;

import com.xdz.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 14543 on 2017/4/11.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    UserService userService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public Object test(){
        userService.testSave();

        logger.info("test");
        logger.error("11111111111111111111111111111");
        return userService.testQuery();
    }

    @RequestMapping(value = "/exportExcelCreateNewBook")
    @ResponseBody
    public void exportExcelCreateNewBook(HttpServletRequest request, HttpServletResponse response){

        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            HSSFSheet sheet = wb.createSheet("Cell comments in POI HSSF");
            HSSFCell cell1 = sheet.createRow(3).createCell(1);
            cell1.setCellValue(new HSSFRichTextString("Hello, World"));
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=testExport.xls");
//            FileOutputStream out = new FileOutputStream("d:\\poi_comment.xls");
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }catch (Exception e){

        }finally {
            try {
                wb.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    @RequestMapping(value = "/exportExcelBookAddContent")
    @ResponseBody
    public void exportExcelBookAddContent(HttpServletRequest request, HttpServletResponse response){

        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            HSSFSheet sheet = wb.createSheet("给第一行加入内容");
            HSSFRow hssfRow = sheet.createRow(0);
            HSSFCell hssfCell = hssfRow.createCell(1);
            hssfCell.setCellValue("第一行第二个单元格加入此内容");
//            HSSFCell cell1 = sheet.createRow(3).createCell(1);
//            cell1.setCellValue(new HSSFRichTextString("Hello, World"));
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=testExport.xls");
//            FileOutputStream out = new FileOutputStream("d:\\poi_comment.xls");
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }catch (Exception e){

        }finally {
            try {
                wb.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    @RequestMapping(value = "/exportExcelXssf")
    @ResponseBody
    public void exportExcelXssf(HttpServletRequest request, HttpServletResponse response){

        Workbook wb = new XSSFWorkbook();
        try {
            XSSFSheet xssfSheet = (XSSFSheet)wb.createSheet("Test XSSFbook");
            XSSFRow xssfRow = xssfSheet.createRow(3);
            XSSFCell xssfCell = xssfRow.createCell(3);
            xssfCell.setCellValue("本内容在第一行 第一个单元格中");

            CellRangeAddress cra=new CellRangeAddress(0, 1, 0, 2);

            xssfSheet.addMergedRegion(cra);
            xssfSheet.createRow(0).createCell(2).setCellValue("此处是合并单元格后的单元格");
//            HSSFCell cell1 = sheet.createRow(3).createCell(1);
//            cell1.setCellValue(new HSSFRichTextString("Hello, World"));
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=testExport.xlsx");
//            FileOutputStream out = new FileOutputStream("d:\\poi_comment.xls");
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                wb.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    @RequestMapping(value = "/uploadExcel")
    @ResponseBody
    public void uploadExcel(HttpServletRequest request, HttpServletResponse response){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(resolver.isMultipart(request)){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
            Iterator iterator = multipartRequest.getFileNames();
            while (iterator.hasNext()){
                MultipartFile file = multipartRequest.getFile(iterator.next().toString());
                String fileName = file.getOriginalFilename();
                String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
                if("xls".equals(suffix)){
                    transformHssf(file);
                }else if("xlsx".equals(suffix)) {
                    transformXssf(file);
                }
            }
        }
    }

    private void transformHssf(MultipartFile file){
        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(file.getInputStream());

            for (int i = 0; i < hssfWorkbook.getNumberOfSheets(); i++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
                for (int j = 0; j < hssfSheet.getPhysicalNumberOfRows(); j++) {
                    HSSFRow hssfRow = hssfSheet.getRow(j);
                    for (int k = 0; k < hssfRow.getPhysicalNumberOfCells(); k++) {
                        HSSFCell hssfCell = hssfRow.getCell(k);
                        System.out.println(hssfCell.getStringCellValue());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void transformXssf(MultipartFile file){
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
            for (int i = 0; i < xssfWorkbook.getNumberOfSheets(); i++) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
                Header header = xssfSheet.getHeader();
                System.out.println("Center : " + header.getCenter());
                System.out.println("Right : " + header.getRight());
                System.out.println("Left : " + header.getLeft());
                for (int j = 0; j < xssfSheet.getPhysicalNumberOfRows(); j++) {
                    XSSFRow xssfRow = xssfSheet.getRow(j);
                    for (int k = 0; k < xssfRow.getPhysicalNumberOfCells(); k++) {
                        XSSFCell xssfCell = xssfRow.getCell(k);
                        System.out.print(xssfCell.toString() + " \t");
                    }
                    System.out.println();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}