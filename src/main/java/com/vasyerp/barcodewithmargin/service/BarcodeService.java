package com.vasyerp.barcodewithmargin.service;

import com.vasyerp.barcodewithmargin.repo.*;
import lombok.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.*;
import org.springframework.core.io.*;
import org.springframework.stereotype.*;

import java.io.*;

@Service
@RequiredArgsConstructor
public class BarcodeService {
    private final BarcodeItemsRepository barcodeItemsRepository;

    private final ResourceLoader resourceLoader;

    public String generateReport(int left, int top) throws Exception {
        String path = "C:\\Users\\kushparsaniya\\Desktop\\jasper\\barcode.pdf";

        Resource resource = resourceLoader.getResource("classpath:barcode.jrxml");
        InputStream inputStream = resource.getInputStream();
        JasperDesign design = JRXmlLoader.load(inputStream);
        int maxX = 65; //I define it so that elements is not out of report
        int maxY = 65;

        int pageWidth = design.getPageWidth();
        int intitalLeftMargin = design.getLeftMargin();
        int intitalRightMargin = design.getRightMargin();
        int intitalTopMargin = design.getTopMargin();

        //Check that not less then 0 and not more then my max
        int newLeftMargin = Math.min(Math.max(intitalLeftMargin + left, 0), maxX);
        int newTopMargin = Math.min(Math.max(intitalTopMargin + top, 0), maxY);

        //set our new margins
        int newColumWidth = (pageWidth - newLeftMargin - intitalRightMargin) / 4;
        design.setLeftMargin(newLeftMargin);
        design.setTopMargin(newTopMargin);
        design.setColumnWidth(newColumWidth);

        JasperReport jasperReport = JasperCompileManager.compileReport(design);


        // data from DB
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(barcodeItemsRepository.findAll());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, path);
        return "Report Generated in path : " + path;
    }

}
