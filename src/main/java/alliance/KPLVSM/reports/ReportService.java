package alliance.KPLVSM.reports;

import alliance.KPLVSM.dao.StaffRepository;
import alliance.KPLVSM.dao.VisitorRepository;
import alliance.KPLVSM.model.Staff;
import alliance.KPLVSM.model.Visitor;
import alliance.KPLVSM.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private StaffRepository staffRepository;
    private VisitorRepository visitorRepository;
    private VisitorService visitorService;

    @Autowired
    public ReportService(StaffRepository staffRepository, VisitorRepository visitorRepository, VisitorService visitorService){
        this.staffRepository= staffRepository;
        this.visitorRepository= visitorRepository;
        this.visitorService= visitorService;

    }
    public AllReportData generateReport(){
        List<Staff> staffList = staffRepository.findAll();
        List<Visitor> visitorList= visitorRepository.findAll();
        List<Visitor> childVisitorList= visitorService.findAllChildVisitors();
        List<Visitor> studentVisitorList= visitorService.findAllStudentVisitors();
        List<Visitor> adultVisitorList= visitorService.findAllAdultVisitors();
        List<Visitor> femaleVisitorList = visitorService.findAllFemaleVisitors();
        List<Visitor> maleVisitorList= visitorService.findAllMaleVisitors();

        AllReportData reportData= new AllReportData();

        reportData.setStaffList(staffList);
        reportData.setVisitorList(visitorList);
        reportData.setChildVisitor(childVisitorList);
        reportData.setStudentVisitor(studentVisitorList);
        reportData.setAdultVisitor(adultVisitorList);
        reportData.setFemaleVisitor(femaleVisitorList);
        reportData.setMaleVisitor(maleVisitorList);

        return reportData;


    }
}
