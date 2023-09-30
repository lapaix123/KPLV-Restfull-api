package alliance.KPLVSM.controller;

import alliance.KPLVSM.reports.AllReportData;
import alliance.KPLVSM.reports.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService){
        this.reportService=reportService;
    }
    @GetMapping("/allData")
    public ResponseEntity<AllReportData> getAllDataReport(){
        AllReportData reportData = reportService.generateReport();
        return ResponseEntity.ok(reportData);

    }
    @GetMapping("/allData/pdf")
    public void generatePDFReport(HttpServletResponse response){
        AllReportData reportData = reportService.generateReport();

        try (PDDocument document = new PDDocument()) {
                PDPage page= new PDPage();
                document.addPage(page);

                PDPageContentStream contentStream = new PDPageContentStream( document, page);
               contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(25, 700);

                //write report data to the PDF content stream

                contentStream.showText("Staff Report: " + reportData.getStaffList());
                contentStream.newLine();
                contentStream.showText("Visitor Report: " + reportData.getVisitorList());
                contentStream.newLine();
                contentStream.showText("Adult Visitor Report: " + reportData.getAdultVisitor());
                contentStream.newLine();
                contentStream.showText("Child Visitor Report: " + reportData.getChildVisitor());
                contentStream.newLine();
                contentStream.showText("Student Visitor Report: " + reportData.getStudentVisitor());
                contentStream.newLine();
                contentStream.showText("Female Visitor Report: " + reportData.getFemaleVisitor());
                contentStream.newLine();
                contentStream.showText("Male Visitor Report: " + reportData.getMaleVisitor());
                contentStream.newLine();

                contentStream.endText();
                contentStream.close();

                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=report.pdf");

                document.save(response.getOutputStream());

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
